package com.base.framework.admin.service;

import com.base.framework.admin.model.dto.role.SysRoleAddDTO;
import com.base.framework.admin.model.dto.role.SysRoleRequestDTO;
import com.base.framework.utils.ResultVo;
import com.github.pagehelper.PageInfo;

/**
 * @Author: 郭郭
 * @Create: 2025/8/18
 * @Description:
 **/
public interface SysRoleService {
    /**
     * 保存角色
     * @return Long
     */
    ResultVo<Long> save(SysRoleAddDTO sysRoleAddDTO);

    /**
     * 角色分页
     * @param params SysRoleRequestDTO
     * @return PageInfo
     */
    PageInfo queryRoleList(SysRoleRequestDTO params);

    /**
     * 删除角色
     * @param id void
     */
    ResultVo<Boolean> deleteById(String id);

}
