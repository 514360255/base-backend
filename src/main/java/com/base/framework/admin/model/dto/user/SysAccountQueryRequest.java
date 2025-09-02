package com.base.framework.admin.model.dto.user;

import com.base.framework.common.PageRequest;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询请求
 * @author guojiuling
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysAccountQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 开放平台id
     */
    private String unionId;

    /**
     * 公众号openId
     */
    private String mpOpenId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;




    /**
     * 关键词
     */
    private String name;

    /**
     * 角色code
     */
    private String roleCode;

    /**
     * 状态
     */
    private Long isActive;

    private static final long serialVersionUID = 1L;
}