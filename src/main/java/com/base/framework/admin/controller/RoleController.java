package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.role.SysRoleAddDTO;
import com.base.framework.admin.model.dto.role.SysRoleRequestDTO;
import com.base.framework.admin.model.vo.SysRoleVO;
import com.base.framework.admin.service.SysRoleService;
import com.base.framework.common.BaseResponse;
import com.base.framework.common.ResultUtils;
import com.base.framework.exception.BusinessException;
import com.base.framework.utils.ResultVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/8/18
 * @Description:
 **/
@RestController
@RequestMapping(ADMIN_PREFIX + "/role")
@Slf4j
public class RoleController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 保存角色
     * @return String
     */
    @PostMapping("save")
    public ResultVo<Long> save(@RequestBody SysRoleAddDTO sysRoleAddDTO) {
        return sysRoleService.save(sysRoleAddDTO);
    }

    /**
     * 角色列表
     * @param params
     * @return BaseResponse<PageInfo>
     */
    @GetMapping("list")
    public BaseResponse<PageInfo> list(SysRoleRequestDTO params) {
        return ResultUtils.success(sysRoleService.queryRoleList(params));
    }

    /**
     * 删除角色
     * @param id String
     */
    @DeleteMapping("{id}")
    public ResultVo<Boolean> deleteById(@PathVariable("id") String id) {
        return sysRoleService.deleteById(id);
    }

    /**
     * 角色详情
     * @param id String
     * @return SysRoleVO
     */
    @GetMapping("{id}")
    public ResultVo<SysRoleVO> getDetailById(@PathVariable("id") String id) {
        return sysRoleService.getDetailById(id);
    }

    /**
     * 修改角色
     * @param params
     * @return
     */
    @PutMapping
    public ResultVo<Boolean> update(@RequestBody SysRoleAddDTO params) {
        return sysRoleService.update(params);
    }

    /**
     * 修改角色状态
     * @return ResultVo<Boolean>
     */
    @PostMapping("update/state")
    public ResultVo<Boolean> updateRoleState(@RequestBody SysRoleAddDTO params) {
        if(params.getId() == null) {
            throw new BusinessException(500,"角色ID不能为空");
        }
        return sysRoleService.updateState(params);
    }
}
