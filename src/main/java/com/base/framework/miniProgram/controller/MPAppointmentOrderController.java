package com.base.framework.miniProgram.controller;

import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentOrderForm;
import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentQueryDTO;
import com.base.framework.miniProgram.service.MPAppointmentOrderService;
import com.base.framework.utils.ResultVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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
    public ResultVo saveAppointmentOrder(@RequestBody MPAppointmentOrderForm params) {
        return appointmentOrderService.saveAppointmentOrder(params);
    }

    /**
     * 查询预约列表
     * @param params MPAppointmentQueryDTO
     * @return ResultVo
     */
    @GetMapping("/list")
    public ResultVo queryAppointmentOrderList(@Param("params") MPAppointmentQueryDTO params) {
        return appointmentOrderService.queryAppointmentOrderList(params);
    }

}
