package com.base.framework.admin.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.AppointmentOrderMapper;
import com.base.framework.admin.model.dto.appointmentOrder.AppointmentOrderQueryDTO;
import com.base.framework.admin.model.entity.AppointmentOrderEntity;
import com.base.framework.admin.model.vo.AppointmentOrderVO;
import com.base.framework.admin.service.AppointmentOrderService;
import com.base.framework.utils.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        List<AppointmentOrderEntity> list = PageHelper.startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                .doSelectPage(() -> appointmentOrderMapper.queryPage(params));
        return ResultVo.ok(new PageInfo<>(CglibUtil.copyList(list, AppointmentOrderVO::new)));
    }

}
