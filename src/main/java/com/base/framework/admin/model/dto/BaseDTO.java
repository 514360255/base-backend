package com.base.framework.admin.model.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 郭郭
 * @Create: 2025/8/19
 * @Description: 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO implements Serializable {

    /**
     * id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long id;

    /**
     * 状态 1:启用，0:禁用
     */
    private int isActive;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * 创建人
     */
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 修改时间
     */
    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private Date updatedAt;

    /**
     * 修改人
     */
    @TableField(value = "updated_by", fill = FieldFill.UPDATE)
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
