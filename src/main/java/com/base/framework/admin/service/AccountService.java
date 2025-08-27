package com.base.framework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.framework.admin.model.dto.user.UserQueryRequest;
import com.base.framework.admin.model.entity.SysAccount;
import com.base.framework.admin.model.vo.LoginUserVO;
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
    PageInfo queryUserList(@Param("params") UserQueryRequest params);

    /**
     * 用户注销
     *
     */
    boolean userLogout();
}
