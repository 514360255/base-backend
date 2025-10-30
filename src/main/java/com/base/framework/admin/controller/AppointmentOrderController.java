package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.appointmentOrder.AppointmentOrderQueryDTO;
import com.base.framework.admin.service.AppointmentOrderService;
import com.base.framework.utils.ResultVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 是否已看诊
     * @param id Long
     * @param isVisit int
     * @return ResultVo
     */
    @PutMapping("{id}/{isVisit}")
    public ResultVo hasVisit(@PathVariable("id") Long id, @PathVariable("isVisit") int isVisit) {
        return appointmentOrderService.hasVisit(id, isVisit);
    }

}
