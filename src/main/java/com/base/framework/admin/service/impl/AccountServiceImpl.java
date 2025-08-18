package com.base.framework.admin.service.impl;

import static com.base.framework.constant.JwtConstant.SALT;
import static com.base.framework.constant.UserConstant.USER_LOGIN_STATE;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.framework.common.ErrorCode;
import com.base.framework.constant.CommonConstant;
import com.base.framework.constant.JwtConstant;
import com.base.framework.exception.BusinessException;
import com.base.framework.admin.mapper.AccountMapper;
import com.base.framework.admin.model.dto.user.UserQueryRequest;
import com.base.framework.admin.model.entity.SysAccount;
import com.base.framework.admin.model.enums.UserRoleEnum;
import com.base.framework.admin.model.vo.LoginUserVO;
import com.base.framework.admin.model.vo.AccountVO;
import com.base.framework.admin.service.AccountService;
import com.base.framework.utils.JwtTokenUtils;
import com.base.framework.utils.SqlUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.base.framework.utils.UserContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户服务实现
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
        SysAccount user = accountMapper.selectByAccount(account, encryptPassword);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match password");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 3. 记录用户的登录态
        UserContextUtils.setUser(user);
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        String token = JwtTokenUtils.createToken(user.getName(), String.valueOf(user.getId()), "", user.getName());
        loginUserVO.setToken(JwtConstant.TOKEN_PREFIX +" "+ token);
        return loginUserVO;
    }
    /**
     * 获取当前登录用户
     *
     */
    @Override
    public LoginUserVO getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        String token = request.getHeader("authorization");
        if (token == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        SysAccount userObj = UserContextUtils.getUser();
        if(token.startsWith(JwtConstant.TOKEN_PREFIX) && userObj == null) {
            userObj = accountMapper.getUserInfoById(JwtTokenUtils.getUserId(token));
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(userObj, loginUserVO);
        return loginUserVO;
    }

    @Override
    public PageInfo queryUserList(UserQueryRequest params) {
        List<SysAccount> list = PageHelper
                .startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                .doSelectPage(() -> accountMapper.queryUserList(params));

        return new PageInfo<>(CglibUtil.copyList(list, AccountVO::new));
    }

    @Override
    public boolean isAdmin(SysAccount user) {
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getRoleCode());
    }

    /**
     * 用户注销
     *
     * @param request
     */
    @Override
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public LoginUserVO getLoginUserVO(SysAccount user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public AccountVO getUserVO(SysAccount user) {
        if (user == null) {
            return null;
        }
        AccountVO userVO = new AccountVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<AccountVO> getUserVO(List<SysAccount> userList) {
        if (CollUtil.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<SysAccount> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String unionId = userQueryRequest.getUnionId();
        String mpOpenId = userQueryRequest.getMpOpenId();
        String userName = userQueryRequest.getUserName();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<SysAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(unionId), "unionId", unionId);
        queryWrapper.eq(StringUtils.isNotBlank(mpOpenId), "mpOpenId", mpOpenId);
        queryWrapper.eq(StringUtils.isNotBlank(userRole), "userRole", userRole);
        queryWrapper.like(StringUtils.isNotBlank(userProfile), "userProfile", userProfile);
        queryWrapper.like(StringUtils.isNotBlank(userName), "userName", userName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}
