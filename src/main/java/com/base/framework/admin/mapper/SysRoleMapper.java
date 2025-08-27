package com.base.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.BaseDTO;
import com.base.framework.admin.model.dto.role.SysRoleAddDTO;
import com.base.framework.admin.model.dto.role.SysRoleRequestDTO;
import com.base.framework.admin.model.entity.SysRoleEntity;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/8/18
 * @Description:
 **/
public interface SysRoleMapper extends BaseMapper<BaseDTO> {

    /**
     * 保存角色
     */
    Long save(SysRoleAddDTO sysRoleAddDTO);

    /**
     * 角色列表
     * @return SysRoleEntity
     */
    List<SysRoleEntity> queryUserList(SysRoleRequestDTO params);

    /**
     * 根据code查询
     * @param code String
     * @return SysRoleEntity
     */
    SysRoleEntity getDetailByCode(String code);

    /**
     * 根据id查询
     * @param id String
     * @return SysRoleEntity
     */
    SysRoleEntity getDetailById(String id);

    /**
     * 删除角色
     * @param id String
     */
    void deleteById(String id);

}
