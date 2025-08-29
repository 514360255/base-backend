package com.base.framework.admin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/8/29
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuEntity extends BaseEntity {

    /**
     * 菜单名
     */
    private String name;

    /**
     * 树关系路径
     */
    private String path;

    /**
     * 组件地址
     */
    private Long parentId;

    /**
     * 组件地址
     */
    private String pathname;

    /**
     * icon图标
     */
    private String icon;

    /**
     * 是否显示
     */
    private int isShow;

    /**
     * 排序
     */
    private int sortOrder;

    /**
     * 菜单类型 0:菜单 1:按钮
     */
    private int type;

}
