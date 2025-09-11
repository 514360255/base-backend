package com.base.framework.admin.model.dto.user;

import com.base.framework.admin.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/9/11
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SysAccountPasswordDTO extends BaseDTO implements Serializable {

    /**
     * 旧密码
     */
    private String password;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认密码
     */
    private String confirmPassword;

}
