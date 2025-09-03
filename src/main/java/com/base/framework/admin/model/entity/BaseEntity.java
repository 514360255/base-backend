package com.base.framework.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 郭郭
 * @Create: 2025/8/8
 * @Description:
 **/
@Data
public class BaseEntity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 状态 1:启用，0:禁用
     */
    private int isActive;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 删除人
     */
    private String deletedBy;

    /**
     * 删除时间
     */
    private Date deletedAt;

    /**
     * 是否删除
     */
    private int isDelete;

    /**
     * 账号id
     */
    private Long accountId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
