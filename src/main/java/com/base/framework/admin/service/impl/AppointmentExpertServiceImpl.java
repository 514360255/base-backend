package com.base.framework.admin.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.AppointmentExpertMapper;
import com.base.framework.admin.model.dto.appointmentExpert.AppointmentExpertForm;
import com.base.framework.admin.model.dto.appointmentExpert.AppointmentExpertQuery;
import com.base.framework.admin.model.dto.appointmentExpert.ExpertList;
import com.base.framework.admin.model.entity.AppointmentExpertEntity;
import com.base.framework.admin.model.vo.AppointmentExpertVO;
import com.base.framework.admin.service.AppointmentExpertService;
import com.base.framework.miniProgram.model.entity.MPAppointmentExpertEntity;
import com.base.framework.utils.ResultVo;
import com.base.framework.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/10/13
 * @Description:
 **/
@Service
@Slf4j
public class AppointmentExpertServiceImpl implements AppointmentExpertService {

    @Resource
    AppointmentExpertMapper appointmentExpertMapper;

    @Override
    public ResultVo queryPage(AppointmentExpertQuery params) {
        String roleCode = SecurityUtils.getRoleCode();
        if(!"SUPER_ADMIN".equals(roleCode)) {
            params.setAccountId(SecurityUtils.getCurrentUserId());
        }
        int total = appointmentExpertMapper.countTotal(params);
        List<AppointmentExpertEntity> list = PageHelper.startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                .doSelectPage(() -> appointmentExpertMapper.queryPage(params));
        list.sort(Comparator.comparing(AppointmentExpertEntity::getSortOrder, Comparator.nullsFirst(Integer::compareTo)));
        PageInfo<AppointmentExpertVO> pageInfo = new PageInfo<>(CglibUtil.copyList(list, AppointmentExpertVO::new));
        pageInfo.setTotal(total);
        return ResultVo.ok(pageInfo);
    }

    @Override
    @Transactional
    public ResultVo save(AppointmentExpertForm params) {
        appointmentExpertMapper.save(params.getHospitalId(), params.getAccountId(), params.getExpertList());
        return ResultVo.ok(params.getId());
    }

    @Override
    @Transactional
    public ResultVo update(ExpertList params) {
        appointmentExpertMapper.update(params);
        return ResultVo.ok(true);
    }

    @Override
    public ResultVo delete(Long id) {
        appointmentExpertMapper.delete(id);
        return ResultVo.ok(true);
    }

}
