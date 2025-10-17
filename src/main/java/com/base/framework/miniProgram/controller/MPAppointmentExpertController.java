package com.base.framework.miniProgram.controller;

import com.base.framework.miniProgram.service.MPAppointmentExpertService;
import com.base.framework.utils.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.MINI_PROGRAM_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/10/17
 * @Description:
 **/
@RestController
@RequestMapping(MINI_PROGRAM_PREFIX + "appointment/expert")
public class MPAppointmentExpertController {

    @Resource
    MPAppointmentExpertService mpAppointmentExpertService;

    /**
     * 预约专家列表
     * @param hospitalId Long
     * @return ResultVo
     */
    @GetMapping("{hospitalId}")
    public ResultVo queryExpertList(@PathVariable("hospitalId") Long hospitalId) {
        return mpAppointmentExpertService.queryExpertList(hospitalId);
    }

}
