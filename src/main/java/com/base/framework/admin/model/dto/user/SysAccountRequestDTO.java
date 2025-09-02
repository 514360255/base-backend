package com.base.framework.admin.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户登录请求
 * @author guojiuling
 */
@Data
public class SysAccountRequestDTO implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String account;

    private String password;
}
