package com.base.framework.miniProgram.model.vo;

import lombok.Data;

/**
 * @Author: 郭郭
 * @Create: 2025/10/11
 * @Description:
 **/
@Data
public class MPAccountLoginVO {

    /**
     *  id
     */
    private String id;

    /**
     *  用户名
     */
    private String name;

    /**
     *  年龄
     */
    private Integer age;

    /**
     *  手机号
     */
    private String mobile;

    /**
     *  token
     */
    private String token;

}
