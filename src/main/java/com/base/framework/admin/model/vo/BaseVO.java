package com.base.framework.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 郭郭
 * @Create: 2025/8/14
 * @Description:
 **/
@Data
public class BaseVO implements Serializable {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;

    /**
     * 修改人
     */
    private String updatedBy;
}
