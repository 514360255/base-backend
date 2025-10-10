package com.base.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.user.SysAccountFormDTO;
import com.base.framework.admin.model.dto.user.SysAccountPasswordDTO;
import com.base.framework.admin.model.dto.user.SysAccountQueryRequest;
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
    SysAccount getAccountInfo(String account, String password);

    /**
     * 根据账号查询用户信息
     * @param account String
     * @return SysAccount
     */
    SysAccount getAccount(String account);

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
    List<SysAccount> queryUserList(SysAccountQueryRequest et);

    /**
     * 保存账号
     * @param params  SysAccountFormDTO
     * @return SysAccount
     */
    Long save(SysAccountFormDTO params);

    /**
     * 修改账号
     * @param params  SysAccountFormDTO
     */
    void update(SysAccountFormDTO params);

    /**
     * 修改密码
     * @param params  SysAccountFormDTO
     */
    void updatePassword(SysAccountPasswordDTO params);

    /**
     * 修改状态
     * @param params  SysAccountFormDTO
     */
    void updateState(SysAccountFormDTO params);

    /**
     * 删除账号
     * @param id Long
     * @param userName String
     */
    void delete(Long id, String userName);

    /**
     * 获取管理员账号
     * @return
     */
    List<SysAccount> getAdminUser();

}




