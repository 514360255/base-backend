package com.base.framework.admin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/9/4
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictEntity extends BaseEntity {

    /**
     * 上级字典名
     */
    private String parentName;

    /**
     * 上级字典ID
     */
    private Long parentId;

    /**
     * 字典名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private int sortOrder;

}
