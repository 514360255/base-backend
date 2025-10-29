package com.base.framework.admin.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.SysRoleMapper;
import com.base.framework.admin.mapper.SysRoleMenuMappingMapper;
import com.base.framework.admin.model.dto.SysRoleMenuMapping;
import com.base.framework.admin.model.dto.menu.SysMenuFormDTO;
import com.base.framework.admin.model.dto.role.SysRoleFormDTO;
import com.base.framework.admin.model.dto.role.SysRoleRequestDTO;
import com.base.framework.admin.model.entity.SysRoleEntity;
import com.base.framework.admin.model.vo.SysRoleVO;
import com.base.framework.admin.service.SysRoleService;
import com.base.framework.common.ErrorCode;
import com.base.framework.exception.BusinessException;
import com.base.framework.utils.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/8/18
 * @Description:
 **/
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleMenuMappingMapper sysRoleMenuMappingMapper;

    private void saveMapping(SysRoleFormDTO params, Long id) {

        List<SysRoleMenuMapping> mappings = new ArrayList<>();
        params.getMenuIds().forEach(item -> {
            SysRoleMenuMapping sysRoleMenuMapping = new SysRoleMenuMapping();
            sysRoleMenuMapping.setMenuId(item);
            sysRoleMenuMapping.setRoleId(params.getId());
            mappings.add(sysRoleMenuMapping);
        });
        sysRoleMenuMappingMapper.save(mappings);
    }

    @Override
    @Transactional
    public ResultVo<Long> save(SysRoleFormDTO params) {
        SysRoleEntity sysRoleEntity = sysRoleMapper.getDetailByCode(params.getCode());
        if(sysRoleEntity != null) {
            throw new BusinessException(ErrorCode.REPEAT_ERROR, ErrorCode.REPEAT_ERROR.getMessage());
        }
        sysRoleMapper.save(params);
        if(params.getId() == null) {
            throw new BusinessException(500, "角色写入失败");
        }
        this.saveMapping(params, params.getId());
        return ResultVo.ok(params.getId());
    }

    @Override
    public ResultVo queryRoleList(SysRoleRequestDTO params) {
        int total = sysRoleMapper.countTotal(params);
        List<SysRoleEntity> list =
                PageHelper.startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                        .doSelectPage(() -> sysRoleMapper.queryUserList(params));
        PageInfo<SysRoleVO> pageInfo = new PageInfo<>(CglibUtil.copyList(list, SysRoleVO::new));
        pageInfo.setTotal(total);
        return ResultVo.ok(pageInfo);
    }

    @Override
    @Transactional
    public ResultVo<Boolean> deleteById(String id) {
        this.getDetailById(id);
        sysRoleMapper.deleteById(id);
        sysRoleMenuMappingMapper.delete(Long.valueOf(id));
        return ResultVo.ok(true);
    }

    @Override
    public ResultVo<SysRoleVO> getDetailById(String id) {
        SysRoleEntity sysRoleEntity = sysRoleMapper.getDetailById(id);
        if(sysRoleEntity == null) {
            throw new BusinessException(500, "角色不存在");
        }
        SysRoleVO sysRoleVO = new SysRoleVO();
        BeanUtils.copyProperties(sysRoleEntity, sysRoleVO);
        return ResultVo.ok(sysRoleVO);
    }

    @Override
    @Transactional
    public ResultVo<Boolean> update(SysRoleFormDTO params){
        this.getDetailById(String.valueOf(params.getId()));
        sysRoleMapper.update(params);
        sysRoleMenuMappingMapper.delete(params.getId());
        this.saveMapping(params, params.getId());
        return ResultVo.ok(true);
    }

    @Override
    @Transactional
    public ResultVo<Boolean> updateState(SysRoleFormDTO params){
        this.getDetailById(String.valueOf(params.getId()));
        sysRoleMapper.updateState(params);
        return ResultVo.ok(true);
    }

}
