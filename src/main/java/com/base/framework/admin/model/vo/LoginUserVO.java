package com.base.framework.admin.model.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 已登录用户视图（脱敏）
 * @author guojiuling
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginUserVO extends BaseVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色Code
     */
    private String roleCode;

    /**
     * token
     */
    private String token;

    private static final long serialVersionUID = 1L;
}