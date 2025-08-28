package com.base.framework.admin.model.dto.role;

import com.base.framework.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/8/19
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleRequestDTO extends PageRequest implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 角色名称
     */
    private  String name;

    /**
     * 角色CODE
     */
    private  String code;

    /**
     * 角色状态
     */
    private int isActive;

}
