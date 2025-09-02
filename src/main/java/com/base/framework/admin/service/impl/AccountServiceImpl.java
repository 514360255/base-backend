package com.base.framework.admin.service.impl;

import static com.base.framework.constant.JwtConstant.SALT;

import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.framework.admin.model.dto.user.SysAccountFormDTO;
import com.base.framework.admin.model.vo.CustomUserDetailsVO;
import com.base.framework.common.ErrorCode;
import com.base.framework.constant.JwtConstant;
import com.base.framework.exception.BusinessException;
import com.base.framework.admin.mapper.AccountMapper;
import com.base.framework.admin.model.dto.user.SysAccountQueryRequest;
import com.base.framework.admin.model.entity.SysAccount;
import com.base.framework.admin.model.vo.LoginUserVO;
import com.base.framework.admin.model.vo.AccountVO;
import com.base.framework.admin.service.AccountService;
import com.base.framework.utils.JwtTokenUtils;
import java.util.List;
import javax.annotation.Resource;

import com.base.framework.utils.ResultVo;
import com.base.framework.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * 用户服务实现
 * @author guojiuling
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, SysAccount> implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public LoginUserVO userLogin(String account, String password) {
        // 1. 校验
        if (StringUtils.isAnyBlank(account, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (account.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 查询用户是否存在
        SysAccount user = accountMapper.getAccountInfo(account, encryptPassword);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match password");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 3. 记录用户的登录态
        CustomUserDetailsVO loginUserVO = new CustomUserDetailsVO();
        BeanUtils.copyProperties(user, loginUserVO);
        String token = JwtTokenUtils.createToken(user.getName(), String.valueOf(user.getId()), "", user.getName());
        loginUserVO.setToken(JwtConstant.TOKEN_PREFIX +" "+ token);
        SecurityUtils.setCurrentUser(loginUserVO);
        return loginUserVO;
    }
    /**
     * 获取当前登录用户
     *
     */
    @Override
    public LoginUserVO getLoginUser(String token) {
        Long userId = JwtTokenUtils.getUserId(token);
        CustomUserDetailsVO customUserDetailsVO = SecurityUtils.getCurrentUser();
        if(customUserDetailsVO == null || customUserDetailsVO.getId() == null) {
            SysAccount sysAccount = accountMapper.getUserInfoById(userId);
            if(sysAccount == null) {
                log.info("用户不存在: " + userId);
                throw new BusinessException(ErrorCode.NO_ACCOUNT, ErrorCode.NO_ACCOUNT.getMessage());
            }
            CustomUserDetailsVO userInfo = new CustomUserDetailsVO();
            BeanUtils.copyProperties(sysAccount, userInfo);
            customUserDetailsVO = userInfo;
        }
        customUserDetailsVO.setToken(token);
        return customUserDetailsVO;
    }

    @Override
    public PageInfo queryUserList(SysAccountQueryRequest params) {
        List<SysAccount> list = PageHelper
                .startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                .doSelectPage(() -> accountMapper.queryUserList(params));
        String userName = SecurityUtils.getCurrentUsername();
        System.out.println("当前用户：" + userName);
        return new PageInfo<>(CglibUtil.copyList(list, AccountVO::new));
    }

    private SysAccount getUserInfoById(Long id) {
        if(id == null) {
            throw new BusinessException(500, "用户id不能为空");
        }
        SysAccount sysAccount = accountMapper.getUserInfoById(id);
        if(sysAccount == null) {
            throw new BusinessException(500, "用户不存在");
        }
        return sysAccount;
    }

    @Override
    public ResultVo<AccountVO> getUserById(Long id) {
        SysAccount sysAccount = this.getUserInfoById(id);
        AccountVO accountVO = new AccountVO();
        BeanUtils.copyProperties(sysAccount, accountVO);
        return ResultVo.ok(accountVO);
    }

    @Override
    @Transactional
    public ResultVo<Boolean> delete(Long id) {
        this.getUserInfoById(id);
        String userName = SecurityUtils.getCurrentUsername();
        accountMapper.delete(id, userName);

        return ResultVo.ok(true);
    }

    @Override
    @Transactional
    public ResultVo<Boolean> updateState(SysAccountFormDTO params) {
        this.getUserInfoById(params.getId());
        accountMapper.updateState(params);
        return ResultVo.ok(true);
    }

    @Override
    @Transactional
    public ResultVo<Long> save(SysAccountFormDTO params) {
        SysAccount sysAccount = accountMapper.getAccount(params.getAccount());
        if(sysAccount != null) {
            throw new BusinessException(500, "账号已存在");
        }
        if(params.getPassword() == null) {
            params.setPassword("123456");
        }
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + params.getPassword()).getBytes());
        params.setPassword(encryptPassword);
        accountMapper.save(params);
        return ResultVo.ok(params.getId());
    }

    @Override
    @Transactional
    public ResultVo<Boolean> update(SysAccountFormDTO params) {
        SysAccount sysAccount = this.getUserInfoById(params.getId());
        if(params.getPassword() != null) {
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + params.getPassword()).getBytes());
            params.setPassword(encryptPassword);
        }else {
            params.setPassword(sysAccount.getPassword());
        }
        accountMapper.update(params);
        return ResultVo.ok(true);
    }

    /**
     * 用户注销
     */
    @Override
    public boolean userLogout() {
        // 移除登录态
        SecurityUtils.removeCurrentUser();
        return true;
    }
}
