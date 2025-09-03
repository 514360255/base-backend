package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.menu.SysMenuFormDTO;
import com.base.framework.admin.model.dto.menu.SysMenuRequestDTO;
import com.base.framework.admin.model.vo.SysMenuVO;
import com.base.framework.admin.service.SysMenuService;
import com.base.framework.utils.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/8/29
 * @Description:
 **/
@RestController
@RequestMapping(ADMIN_PREFIX + "/menu")
public class SysMenuController {

    @Resource
    SysMenuService sysMenuService;

    /**
     * 获取菜单列表
     * @param params SysMenuRequestDTO
     * @return ResultVo
     */
    @GetMapping("page")
    public ResultVo queryMenuPage(SysMenuRequestDTO params) {
        return sysMenuService.queryMenuPage(params);
    }

    /**
     * 根据角色查询菜单
     * @return
     */
    @GetMapping("list")
    public ResultVo queryMenuListByRoleCode() {
        return sysMenuService.queryMenuListByRoleCode();
    }

    /**
     * 保存菜单
     * @param params SysMenuFormDTO
     * @return ResultVo
     */
    @PostMapping
    public ResultVo<Long> save(@RequestBody SysMenuFormDTO params) {
        return sysMenuService.save(params);
    }

    /**
     * 修改菜单
     * @param params SysMenuFormDTO
     * @return ResultVo<Boolean>
     */
    @PutMapping
    public ResultVo<Boolean> update(@RequestBody SysMenuFormDTO params) {
        return sysMenuService.update(params);
    }

    /**
     * 删除菜单
     * @param id Long
     * @return ResultVo<Boolean>
     */
    @DeleteMapping("{id}")
    public ResultVo<Boolean> delete(@PathVariable("id") Long id) {
        return sysMenuService.delete(id);
    }

    /**
     * 菜单详情
     * @param id Long
     * @return ResultVo<SysMenuVO>
     */
    @GetMapping("{id}")
    public ResultVo<SysMenuVO> getDetailById(@PathVariable("id") Long id) {
        return sysMenuService.getDetailById(id);
    }

    /**
     * 修改状态
     * @param params SysMenuFormDTO
     * @return ResultVo<Boolean>
     */
    @PostMapping("update/state")
    public ResultVo<Boolean> updateState(@RequestBody SysMenuFormDTO params) {
        return sysMenuService.updateState(params);
    }

    /**
     * 修改显示隐藏
     * @param params SysMenuFormDTO
     * @return ResultVo<Boolean>
     */
    @PostMapping("update/show")
    public ResultVo<Boolean> updateShow(@RequestBody SysMenuFormDTO params) {
        return sysMenuService.updateShow(params);
    }

}
