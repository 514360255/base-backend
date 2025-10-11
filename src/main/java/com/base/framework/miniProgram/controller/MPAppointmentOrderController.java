package com.base.framework.miniProgram.controller;

import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentOrderForm;
import com.base.framework.miniProgram.service.MPAppointmentOrderService;
import com.base.framework.utils.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.MINI_PROGRAM_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@RestController
@RequestMapping(MINI_PROGRAM_PREFIX + "appointment")
public class MPAppointmentOrderController {

    @Resource
    MPAppointmentOrderService appointmentOrderService;

    @PostMapping
    public ResultVo<Long> saveAppointmentOrder(@RequestBody MPAppointmentOrderForm params) {
        return appointmentOrderService.saveAppointmentOrder(params);
    }

}
