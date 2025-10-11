package com.base.framework.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户
 * @author guojiuling
 */
@TableName(value = "sys_account")
@Data
public class SysAccount extends BaseEntity implements Serializable {

    /**
     * 上级Id
     */
    private Long parentId;

    /**
     * 上级姓名
     */
    private String parentName;

    /**
     * 医院名称
     */
    private String hospitalName;

    /**
     * 医院id
     */
    private Long hospitalId;

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
     * 收件人
     */
    private String recipient;

    /**
     * 角色Code
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 密码
     */
    private String password;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}