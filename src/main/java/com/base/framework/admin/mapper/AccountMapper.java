package com.base.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.user.UserQueryRequest;
import com.base.framework.admin.model.entity.SysAccount;

import java.util.List;

/**
 * @author guojiuling
 * @description 用户数据库操作
 */
public interface AccountMapper extends BaseMapper<SysAccount> {

    /**
     * 根据账号密码查询用户信息
     * @param account String
     * @param password String
     * @return SysAccount
     */
    SysAccount selectByAccount(String account, String password);

    /**
     * 获取用户信息
     * @param id String
     * @return SysAccount
     */
    SysAccount getUserInfoById(Long id);

    /**
     * 查询用户列表
     * @param et UserQueryRequest
     * @return List<AccountVO></>
     */
    List<SysAccount> queryUserList(UserQueryRequest et);

}




