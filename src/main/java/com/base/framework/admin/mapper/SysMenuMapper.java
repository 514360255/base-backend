package com.base.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.BaseDTO;
import com.base.framework.admin.model.dto.menu.SysMenuFormDTO;
import com.base.framework.admin.model.dto.menu.SysMenuRequestDTO;
import com.base.framework.admin.model.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/8/18
 * @Description:
 **/
public interface SysMenuMapper extends BaseMapper<BaseDTO> {

    /**
     * 保存角色
     */
    Long save(SysMenuFormDTO sysRoleAddDTO);

    /**
     * 角色列表
     * @return SysRoleEntity
     */
    List<SysMenuEntity> queryMenuPage(SysMenuRequestDTO params);

    /**
     * 根据pathname查询数据
     * @param pathname String
     * @return SysMenuEntity
     */
    SysMenuEntity getDetailByPathname(String pathname);

    /**
     * 删除菜单
     * @param ids List<Long>
     */
    void delete(@Param("list") List<Long> ids);


    /**
     * 根据id查询
     * @param id String
     * @return SysRoleEntity
     */
    SysMenuEntity getDetailById(Long id);

    /**
     * 修改菜单
     * @param params SysRoleRequestDTO
     */
    void update(SysMenuFormDTO params);

    /**
     * 修改菜单状态
     * @param params SysRoleRequestDTO
     */
    void updateState(SysMenuFormDTO params);

}
