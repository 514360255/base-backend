package com.base.framework.miniProgram.model.dto.account;

import lombok.Data;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
@Data
public class MPAuthForm {

    /**
     *  加密数据
     */
    private String iv;

    /**
     * 登录凭证
     */
    private String encryptedData;

    /**
     * 登录code
     */
    private String code;

    /**
     * 医院CODE
     */
    private String hospitalCode;


}
