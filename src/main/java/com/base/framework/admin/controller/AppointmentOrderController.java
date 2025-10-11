package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.appointmentOrder.AppointmentOrderQueryDTO;
import com.base.framework.admin.service.AppointmentOrderService;
import com.base.framework.utils.ResultVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@RestController
@RequestMapping(ADMIN_PREFIX + "appointment")
public class AppointmentOrderController {

    @Resource
    AppointmentOrderService appointmentOrderService;

    @GetMapping
    public ResultVo queryPage(@Param("params") AppointmentOrderQueryDTO params) {
        return appointmentOrderService.queryPage(params);
    }

}
