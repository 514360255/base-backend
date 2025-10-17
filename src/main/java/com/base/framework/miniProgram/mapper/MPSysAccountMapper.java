package com.base.framework.miniProgram.mapper;

import com.base.framework.miniProgram.model.entity.MPSysAccountEntity;

/**
 * @Author: 郭郭
 * @Create: 2025/10/17
 * @Description:
 **/
public interface MPSysAccountMapper {

    /**
     * 根据账号ID获取账号信息
     * @param accountId Long
     * @return MPSysAccountEntity
     */
    MPSysAccountEntity getDetailByAccountId(Long accountId);

}
