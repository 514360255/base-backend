package com.base.framework.admin.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/8/29
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuVO extends BaseVO implements Serializable {

    /**
     * 菜单名
     */
    private String name;

    /**
     * 上级ID
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
     * 排序
     */
    private int sortOrder;

    /**
     * 菜单类型 0:菜单 1:按钮
     */
    private int type;

    /**
     * 是否显示
     */
    private int isShow;

    /**
     * 树形结构
     */
    private List<SysMenuVO> children;

}
