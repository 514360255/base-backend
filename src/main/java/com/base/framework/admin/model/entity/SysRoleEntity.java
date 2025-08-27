package com.base.framework.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/8/18
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_account")
@Data
public class SysRoleEntity extends BaseEntity implements Serializable {

    /**
     * 角色名称
     */
    private String name;


    /**
     * 角色code
     */
    private String code;

}
