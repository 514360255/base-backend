package com.base.framework.admin.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.AppointmentDepartmentMapper;
import com.base.framework.admin.model.dto.appointmentDepartment.AppointmentDepartmentDTO;
import com.base.framework.admin.model.dto.appointmentDepartment.QueryAppointmentDepartmentQuery;
import com.base.framework.admin.model.entity.AppointmentDepartmentEntity;
import com.base.framework.admin.model.vo.AppointmentDepartmentVO;
import com.base.framework.admin.service.AppointmentDepartmentService;
import com.base.framework.exception.BusinessException;
import com.base.framework.utils.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/29
 * @Description:
 **/
@Service
@Slf4j
public class AppointmentDepartmentServiceImpl implements AppointmentDepartmentService {

    @Resource
    AppointmentDepartmentMapper appointmentDepartmentMapper;

    @Override
    public ResultVo queryPage(QueryAppointmentDepartmentQuery params) {
        List<AppointmentDepartmentEntity> list =
                PageHelper.startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                        .doSelectPage(() -> appointmentDepartmentMapper.queryPage(params));
        return ResultVo.ok(new PageInfo<>(CglibUtil.copyList(list, AppointmentDepartmentVO::new)));
    }

    @Override
    @Transactional
    public ResultVo save(AppointmentDepartmentDTO params) {
        Integer integer = appointmentDepartmentMapper.getDetailByName(params.getName());
        if(integer == 1) {
            throw new BusinessException(500, "科室名称重复");
        }
        appointmentDepartmentMapper.save(params);
        return ResultVo.ok(params.getId());
    }

    @Override
    @Transactional
    public ResultVo update(AppointmentDepartmentDTO params) {
        if(params.getId() == null) {
            throw new BusinessException(500, "ID不能为空");
        }
       AppointmentDepartmentEntity appointmentDepartmentEntity = appointmentDepartmentMapper.getDetailById(params.getId());
        if(appointmentDepartmentEntity == null) {
            throw new BusinessException(500, "数据不存在");
        }
        appointmentDepartmentMapper.update(params);
        return ResultVo.ok(true);
    }

    @Override
    public ResultVo queryById(Long id) {
        if(id == null) {
            throw new BusinessException(500, "ID不能为空");
        }
        AppointmentDepartmentEntity appointmentDepartmentEntity = appointmentDepartmentMapper.getDetailById(id);
        AppointmentDepartmentVO appointmentDepartmentVO = new AppointmentDepartmentVO();
        BeanUtils.copyProperties(appointmentDepartmentEntity, appointmentDepartmentVO);
        return ResultVo.ok(appointmentDepartmentVO);
    }

    @Override
    public ResultVo deleteById(Long id) {
        if(id == null) {
            throw new BusinessException(500, "ID不能为空");
        }
        appointmentDepartmentMapper.deleteById(id);
        return ResultVo.ok(true);
    }

}
