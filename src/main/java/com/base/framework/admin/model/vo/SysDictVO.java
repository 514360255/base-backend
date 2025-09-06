package com.base.framework.admin.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/4
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictVO extends BaseVO {

    /**
     * 字典名称
     */
    private String name;

    /**
     * 上级字典名称
     */
    private String parentName;

    /**
     * 上级字典id
     */
    private Long parentId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private int sortOrder;

    /**
     * 下级字典
     */
    private List<SysDictVO> children;

}
