package com.base.framework.admin.model.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户视图（脱敏）
 * @author guojiuling
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountVO extends BaseVO implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    /**
     * 创建时间
     */
    private Date createTime;










    /**
     * 姓名
     */
    private String name;

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
     * 联系电话
     */
    private String phone;

    private static final long serialVersionUID = 1L;
}