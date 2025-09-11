package com.base.framework.admin.model.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/8/19
 * @Description: 
 **/
@Data
public class BaseDTO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 状态 1:启用，0:禁用
     */
    private int isActive;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updatedBy;

    /**
     * 删除人
     */
    private String deletedBy;

    /**
     * 账号id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long accountId;

    /**
     * 是否删除
     */
    private int isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
