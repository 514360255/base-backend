package com.base.framework.admin.service;

import com.base.framework.admin.model.dto.menu.SysMenuFormDTO;
import com.base.framework.admin.model.dto.menu.SysMenuRequestDTO;
import com.base.framework.admin.model.vo.SysMenuVO;
import com.base.framework.utils.ResultVo;

/**
 * @Author: 郭郭
 * @Create: 2025/8/29
 * @Description:
 **/
public interface SysMenuService {

    /**
     * 获取菜单列表
     * @param params SysMenuRequestDTO
     * @return ResultVo
     */
    ResultVo queryMenuPage(SysMenuRequestDTO params);

    /**
     * 根据角色获取菜单列表
     * @return ResultVo
     */
    ResultVo queryMenuListByRoleCode();

    /**
     * 保存菜单
     * @param params SysMenuFormDTO
     * @return ResultVo<Long>
     */
    ResultVo<Long> save(SysMenuFormDTO params);

    /**
     * 保存菜单
     * @param params SysMenuFormDTO
     * @return ResultVo<Long>
     */
    ResultVo<Boolean> update(SysMenuFormDTO params);

    /**
     * 删除菜单
     * @param id Long
     * @return  ResultVo<Boolean>
     */
    ResultVo<Boolean> delete(Long id);

    /**
     * 菜单详情
     * @param id Long
     * @return  ResultVo<Boolean>
     */
    ResultVo<SysMenuVO> getDetailById(Long id);

    /**
     * 修改菜单状态
     * @param id Long
     * @return  ResultVo<Boolean>
     */
    ResultVo<Boolean> updateState(SysMenuFormDTO id);

    /**
     * 修改菜单显示状态
     * @param id Long
     * @return  ResultVo<Boolean>
     */
    ResultVo<Boolean> updateShow(SysMenuFormDTO id);
}
