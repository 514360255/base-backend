package com.base.framework.admin.model.dto.role;

import com.base.framework.admin.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/8/18
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleFormDTO extends BaseDTO {

    /**
     * 角色code
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色状态
     */
    private int isActive;

}
