package com.base.framework.admin.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.SysMenuMapper;
import com.base.framework.admin.mapper.SysRoleMapper;
import com.base.framework.admin.mapper.SysRoleMenuMappingMapper;
import com.base.framework.admin.model.dto.menu.SysMenuFormDTO;
import com.base.framework.admin.model.dto.menu.SysMenuRequestDTO;
import com.base.framework.admin.model.entity.SysMenuEntity;
import com.base.framework.admin.model.entity.SysRoleEntity;
import com.base.framework.admin.model.vo.CustomUserDetailsVO;
import com.base.framework.admin.model.vo.SysMenuVO;
import com.base.framework.admin.service.SysMenuService;
import com.base.framework.exception.BusinessException;
import com.base.framework.utils.MenuTreeUtil;
import com.base.framework.utils.ResultVo;
import com.base.framework.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: 郭郭
 * @Create: 2025/8/29
 * @Description:
 **/
@Service
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    SysMenuMapper sysMenuMapper;

    @Resource
    SysRoleMapper sysRoleMapper;

    @Resource
    SysRoleMenuMappingMapper sysRoleMenuMappingMapper;

    private List<SysMenuEntity> queryMenuList(SysMenuRequestDTO params) {
        List<SysMenuEntity> list =
                PageHelper.startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                        .doSelectPage(() -> sysMenuMapper.queryMenuPage(params));
        return list;
    }

    @Override
    public ResultVo queryMenuPage(SysMenuRequestDTO params) {
        List<SysMenuVO> sysRoleVOList = CglibUtil.copyList(this.queryMenuList(params), SysMenuVO::new);
        return ResultVo.ok(new PageInfo<>(MenuTreeUtil.buildTree(sysRoleVOList)));
    }

    @Override
    public ResultVo queryMenuListByRoleCode() {
        CustomUserDetailsVO customUserDetailsVO = SecurityUtils.getCurrentUser();
        if(customUserDetailsVO == null) {
            throw  new BusinessException(500, "账号未登录");
        }
        List<SysMenuVO> sysRoleVOList;
        if(Objects.equals(customUserDetailsVO.getRoleCode(), "SUPER_ADMIN")) {
            List<SysMenuEntity> sysRoleEntities = sysMenuMapper.getMenusByIds(new ArrayList<>(), customUserDetailsVO.getRoleCode());
            sysRoleVOList = CglibUtil.copyList(sysRoleEntities, SysMenuVO::new);
        }else {
            SysRoleEntity sysRoleEntity = sysRoleMapper.getDetailByCode(customUserDetailsVO.getRoleCode());
            if(sysRoleEntity == null) {
                throw  new BusinessException(500, "角色不存在");
            }
            List<Long> menuIds = sysRoleMenuMappingMapper.getMenuIds(sysRoleEntity.getId());
            if(menuIds == null) {
                return ResultVo.ok(new ArrayList<>());
            }
            List<SysMenuEntity> sysRoleEntities = sysMenuMapper.getMenusByIds(menuIds, customUserDetailsVO.getRoleCode());
            if(sysRoleEntities == null) {
                return ResultVo.ok(new ArrayList<>());
            }
            sysRoleVOList = CglibUtil.copyList(sysRoleEntities, SysMenuVO::new);
        }

        return ResultVo.ok(MenuTreeUtil.buildTree(sysRoleVOList));
    }

    @Override
    @Transactional
    public ResultVo<Long> save(SysMenuFormDTO params) {
        SysMenuEntity sysMenuEntity = sysMenuMapper.getDetailByPathname(params.getPathname());
        if(sysMenuEntity != null) {
            throw new BusinessException(500, "菜单组件地址重复");
        }
        sysMenuMapper.save(params);
        return ResultVo.ok(params.getId());
    }

    @Override
    @Transactional
    public ResultVo<Boolean> update(SysMenuFormDTO params) {
        SysMenuEntity sysMenuEntity = sysMenuMapper.getDetailById(params.getId());
        if(sysMenuEntity == null) {
            throw new BusinessException(500, "菜单不存在");
        }
        sysMenuMapper.update(params);
        return ResultVo.ok(true);
    }

    private static void collectChildrenIds(List<SysMenuEntity> list, Long parentId, List<Long> result, Set<Long> visited) {
        for (SysMenuEntity node : list) {
            if (node.getId().equals(parentId) && visited.add(node.getId())) {
                // 添加自身
                result.add(node.getId());
            }
            if (node.getParentId().equals(parentId)) {
                if (visited.add(node.getId())) {
                    result.add(node.getId());
                }
                // 递归查找子节点
                collectChildrenIds(list, node.getId(), result, visited);
            }
        }
    }

    @Override
    @Transactional
    public ResultVo<Boolean> delete(Long id) {
        SysMenuRequestDTO sysMenuRequestDTO = new SysMenuRequestDTO();
        List<SysMenuEntity> list = this.queryMenuList(sysMenuRequestDTO);
        // 防止重复
        Set<Long> visited = new HashSet<>();
        List<Long> ids = new ArrayList<>();
        collectChildrenIds(list, id, ids, visited);
        if(ids.isEmpty()) {
            ids.add(id);
        }
        // 删除菜单
        sysMenuMapper.delete(ids);

        // 删除角色和菜单关系
        sysRoleMenuMappingMapper.deleteRoleMenuMapping(ids);
        return ResultVo.ok(true);
    }

    @Override
    public ResultVo<SysMenuVO> getDetailById(Long id) {
        SysMenuEntity sysMenuEntity = sysMenuMapper.getDetailById(id);
        if(sysMenuEntity == null) {
            throw new BusinessException(500, "菜单不存在");
        }
        SysMenuVO sysMenuVO = new SysMenuVO();
        BeanUtils.copyProperties(sysMenuEntity, sysMenuVO);
        return ResultVo.ok(sysMenuVO);
    }

    @Override
    @Transactional
    public ResultVo<Boolean> updateState(SysMenuFormDTO params) {
        SysMenuEntity sysMenuEntity = sysMenuMapper.getDetailById(params.getId());
        if(sysMenuEntity == null) {
            throw new BusinessException(500, "菜单不存在");
        }
        sysMenuMapper.updateState(params);
        return ResultVo.ok(true);
    }

    @Override
    @Transactional
    public ResultVo<Boolean> updateShow(SysMenuFormDTO params) {
        SysMenuEntity sysMenuEntity = sysMenuMapper.getDetailById(params.getId());
        if(sysMenuEntity == null) {
            throw new BusinessException(500, "菜单不存在");
        }
        sysMenuMapper.updateShow(params);
        return ResultVo.ok(true);
    }

}
