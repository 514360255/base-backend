package com.base.framework.admin.model.dto;

import lombok.Data;

/**
 * @Author: 郭郭
 * @Create: 2025/8/29
 * @Description:
 **/
@Data
public class SysRoleMenuMapping extends BaseDTO {
    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 角色id
     */
    private Long roleId;
}
