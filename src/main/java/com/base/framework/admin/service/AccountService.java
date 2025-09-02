package com.base.framework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.framework.admin.model.dto.user.SysAccountFormDTO;
import com.base.framework.admin.model.dto.user.SysAccountQueryRequest;
import com.base.framework.admin.model.entity.SysAccount;
import com.base.framework.admin.model.vo.AccountVO;
import com.base.framework.admin.model.vo.LoginUserVO;
import com.base.framework.utils.ResultVo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户服务
 * @author guojiuling
 */
public interface AccountService extends IService<SysAccount> {

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword);

    /**
     * 获取当前登录用户
     *
     */
    LoginUserVO getLoginUser(String token);

    /**
     * 查询用户列表
     * @param params UserQueryRequest
     * @return List<AccountVO></>
     */
    PageInfo queryUserList(@Param("params") SysAccountQueryRequest params);

    /**
     * 保存账号
     * @param params SysAccountFormDTO
     * @return ResultVo<Boolean>
     */
    ResultVo<Long> save(SysAccountFormDTO params);

    /**
     * 保存账号
     * @param params SysAccountFormDTO
     * @return ResultVo<Boolean>
     */
    ResultVo<Boolean> update(SysAccountFormDTO params);

    /**
     * 删除用户
     * @param id Long
     * @return ResultVo<Boolean>
     */
    ResultVo<Boolean> delete(Long id);

    /**
     * 删除用户
     * @param id Long
     * @return ResultVo<AccountVO>
     */
    ResultVo<AccountVO> getUserById(Long id);

    /**
     * 修改状态
     * @param params SysAccountFormDTO
     */
    ResultVo<Boolean> updateState(SysAccountFormDTO params);

    /**
     * 用户注销
     *
     */
    boolean userLogout();
}
