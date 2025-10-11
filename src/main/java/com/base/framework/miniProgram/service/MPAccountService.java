package com.base.framework.miniProgram.service;

import com.base.framework.miniProgram.model.dto.account.AuthForm;
import com.base.framework.utils.ResultVo;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
public interface MPAccountService {

    /**
     * 获取用户信息
     * @param params
     * @return
     */
    ResultVo login(AuthForm params);

}
