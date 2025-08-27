package com.base.framework.admin.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 * @author guojiuling
 */
@Data
public class SysRoleVO extends BaseVO implements Serializable {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色code
     */
    private String code;


    private static final long serialVersionUID = 1L;
}