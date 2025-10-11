package com.base.framework.admin.model.dto.user;

import com.base.framework.admin.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/8/29
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SysAccountFormDTO extends BaseDTO implements Serializable {

    /**
     * 上级id
     */
    private Long parentId;

    /**
     * 医院id
     */
    private Long hospitalId;

    /**
     * 用户名
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
     * 收件人
     */
    private String recipient;

    /**
     * 角色Code
     */
    private String roleCode;

    /**
     * 密码
     */
    private String password;
}
