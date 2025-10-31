package com.base.framework.admin.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.AppointmentOrderMapper;
import com.base.framework.admin.model.dto.appointmentOrder.AppointmentOrderQueryDTO;
import com.base.framework.admin.model.entity.AppointmentOrderEntity;
import com.base.framework.admin.model.vo.AppointmentOrderVO;
import com.base.framework.admin.service.AppointmentOrderService;
import com.base.framework.exception.BusinessException;
import com.base.framework.utils.ResultVo;
import com.base.framework.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@Service
@Slf4j
public class AppointmentOrderServiceImpl implements AppointmentOrderService {

    @Resource
    AppointmentOrderMapper appointmentOrderMapper;

    @Override
    public ResultVo queryPage(AppointmentOrderQueryDTO params) {
        if(!SecurityUtils.isSuperAdmin()) {
            params.setAccountId(SecurityUtils.getCurrentUserId());
        }
        int total = appointmentOrderMapper.countTotal(params);
        List<AppointmentOrderEntity> list = PageHelper.startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                .doSelectPage(() -> appointmentOrderMapper.queryPage(params));
        PageInfo<AppointmentOrderVO> pageInfo = new PageInfo<>(CglibUtil.copyList(list, AppointmentOrderVO::new));
        pageInfo.setTotal(total);
        return ResultVo.ok(pageInfo);
    }

    @Override
    @Transactional
    public ResultVo hasVisit(Long id, int isVisit){
        AppointmentOrderEntity appointmentOrderEntity = appointmentOrderMapper.getDetailById(id);
        if(appointmentOrderEntity == null) {
            throw new BusinessException(500, "预约不存在");
        }
        appointmentOrderMapper.hasVisit(id, isVisit);
        return ResultVo.ok(true);
    }

}
