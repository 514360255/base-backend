package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.appointmentUser.AppointmentUserQueryDTO;
import com.base.framework.admin.service.AppointmentUserService;
import com.base.framework.utils.ResultVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/10/11
 * @Description:
 **/
@RestController
@RequestMapping(ADMIN_PREFIX + "appointment/user")
public class AppointmentUserController {

    @Resource
    AppointmentUserService appointmentUserService;

    @GetMapping
    public ResultVo queryPage(@Param("params") AppointmentUserQueryDTO params) {
        return appointmentUserService.queryPage(params);
    }
}
