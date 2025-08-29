package com.base.framework.admin.model.dto.menu;

import com.base.framework.admin.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/8/29
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuFormDTO extends BaseDTO {

    /**
     * 上级菜单ID
     */
    private Long parentId;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 菜单关系路径
     */
    private String path;

    /**
     * 组件地址
     */
    private String pathname;

    /**
     * icon
     */
    private String icon;

    /**
     * 排序
     */
    private int sortOrder;

    /**
     * 菜单类型
     */
    private int type;

    /**
     * 是否显示
     */
    private int isShow;

}
