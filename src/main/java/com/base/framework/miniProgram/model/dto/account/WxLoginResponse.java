package com.base.framework.miniProgram.model.dto.account;

import lombok.Data;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
@Data
public class WxLoginResponse {
    /**
     * openid
     */
    private String openid;

    /***
     * session_key
     */
    private String session_key;

    /**
     * unionid
     */
    private String unionid;

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;
}
