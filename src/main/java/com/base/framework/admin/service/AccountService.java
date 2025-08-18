package com.base.framework.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.framework.admin.model.dto.user.UserQueryRequest;
import com.base.framework.admin.model.entity.SysAccount;
import com.base.framework.admin.model.vo.LoginUserVO;
import com.base.framework.admin.model.vo.AccountVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
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
    LoginUserVO getLoginUser(HttpServletRequest request);

    /**
     * 查询用户列表
     * @param params UserQueryRequest
     * @return List<AccountVO></>
     */
    PageInfo queryUserList(@Param("params") UserQueryRequest params);

    /**
     * 是否为管理员
     *
     */
    boolean isAdmin(SysAccount user);

    /**
     * 用户注销
     *
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户信息
     *
     */
    LoginUserVO getLoginUserVO(SysAccount user);

    /**
     * 获取脱敏的用户信息
     *
     */
    AccountVO getUserVO(SysAccount user);

    /**
     * 获取脱敏的用户信息
     *
     */
    List<AccountVO> getUserVO(List<SysAccount> userList);

    /**
     * 获取查询条件
     *
     */
    QueryWrapper<SysAccount> getQueryWrapper(UserQueryRequest userQueryRequest);

}
