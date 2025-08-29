package com.base.framework.admin.service;

import com.base.framework.admin.model.dto.role.SysRoleFormDTO;
import com.base.framework.admin.model.dto.role.SysRoleRequestDTO;
import com.base.framework.admin.model.vo.SysRoleVO;
import com.base.framework.utils.ResultVo;

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
    ResultVo<Long> save(SysRoleFormDTO sysRoleAddDTO);

    /**
     * 角色分页
     * @param params SysRoleRequestDTO
     * @return PageInfo
     */
    ResultVo queryRoleList(SysRoleRequestDTO params);

    /**
     * 删除角色
     * @param id void
     */
    ResultVo<Boolean> deleteById(String id);

    /**
     * 角色详情
     * @param id String
     * @return SysRoleVO
     */
    ResultVo<SysRoleVO> getDetailById(String id);

    /**
     * 修改角色
     * @param params SysRoleRequestDTO
     * @return Boolean
     */
    ResultVo<Boolean> update(SysRoleFormDTO params);


    /**
     * 修改角色状态
     * @param params SysRoleRequestDTO
     * @return Boolean
     */
    ResultVo<Boolean> updateState(SysRoleFormDTO params);

}
