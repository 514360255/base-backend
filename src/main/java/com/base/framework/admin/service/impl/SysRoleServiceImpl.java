package com.base.framework.admin.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.SysRoleMapper;
import com.base.framework.admin.model.dto.role.SysRoleAddDTO;
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

import javax.annotation.Resource;
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

    @Override
    public ResultVo<Long> save(SysRoleAddDTO sysRoleAddDTO) {
        SysRoleEntity sysRoleEntity = sysRoleMapper.getDetailByCode(sysRoleAddDTO.getCode());
        if(sysRoleEntity != null) {
            throw new BusinessException(ErrorCode.REPEAT_ERROR, ErrorCode.REPEAT_ERROR.getMessage());
        }
        Long id = sysRoleMapper.save(sysRoleAddDTO);
        return ResultVo.ok(id);
    }

    @Override
    public PageInfo queryRoleList(SysRoleRequestDTO params) {
        List<SysRoleEntity> list =
                PageHelper.startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                        .doSelectPage(() -> sysRoleMapper.queryUserList(params));
        return new PageInfo<>(CglibUtil.copyList(list, SysRoleVO::new));
    }

    @Override
    public ResultVo<Boolean> deleteById(String id) {
        this.getDetailById(id);
        sysRoleMapper.deleteById(id);
        return ResultVo.ok(true);
    }

    @Override
    public ResultVo<SysRoleVO> getDetailById(String id) {
        SysRoleEntity sysRoleEntity = sysRoleMapper.getDetailById(id);
        if(sysRoleEntity == null) {
            throw new BusinessException(401, "角色不存在");
        }
        SysRoleVO sysRoleVO = new SysRoleVO();
        BeanUtils.copyProperties(sysRoleEntity, sysRoleVO);
        return ResultVo.ok(sysRoleVO);
    }

    @Override
    public ResultVo<Boolean> update(SysRoleAddDTO params){
        this.getDetailById(String.valueOf(params.getId()));
        sysRoleMapper.update(params);
        return ResultVo.ok(true);
    }

    @Override
    public ResultVo<Boolean> updateState(SysRoleAddDTO params){
        this.getDetailById(String.valueOf(params.getId()));
        sysRoleMapper.updateState(params);
        return ResultVo.ok(true);
    }

}
