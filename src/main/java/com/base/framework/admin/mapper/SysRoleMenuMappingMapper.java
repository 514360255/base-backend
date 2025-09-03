package com.base.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.BaseDTO;
import com.base.framework.admin.model.dto.SysRoleMenuMapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/8/18
 * @Description:
 **/
public interface SysRoleMenuMappingMapper extends BaseMapper<BaseDTO> {

    /**
     * 保存角色
     */
    void save(@Param("list") List<SysRoleMenuMapping> mapping);

    /**
     * 删除
     * @param id Long
     */
    void delete(Long id);

    /**
     * 根据角色id获取菜单id
     * @param roleId Long
     * @return List<Long>
     */
    List<Long> getMenuIds(Long roleId);

    /**
     * 删除菜单关系
     * @param ids List<Long>
     */
    void deleteRoleMenuMapping(@Param("list") List<Long> ids);
}
