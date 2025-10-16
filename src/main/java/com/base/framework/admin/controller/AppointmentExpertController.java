package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.appointmentExpert.AppointmentExpertForm;
import com.base.framework.admin.model.dto.appointmentExpert.AppointmentExpertQuery;
import com.base.framework.admin.model.dto.appointmentExpert.ExpertList;
import com.base.framework.admin.service.AppointmentExpertService;
import com.base.framework.utils.ResultVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/10/11
 * @Description:
 **/
@RestController
@RequestMapping(ADMIN_PREFIX + "appointment/expert")
public class AppointmentExpertController {

    @Resource
    AppointmentExpertService appointmentExpertService;

    /**
     * 预约专家列表
     * @param params AppointmentExpertQuery
     * @return ResultVo
     */
    @GetMapping
    public ResultVo queryPage(@Param("params") AppointmentExpertQuery params) {
        return appointmentExpertService.queryPage(params);
    }

    /**
     * 保存预约专家信息
     * @param params AppointmentExpertForm
     * @return ResultVo
     */
    @PostMapping
    public ResultVo save(@RequestBody AppointmentExpertForm params) {
        return appointmentExpertService.save(params);
    }

    /**
     * 修改预约专家信息
     * @param params AppointmentExpertForm
     * @return ResultVo
     */
    @PutMapping
    public ResultVo update(@RequestBody ExpertList params) {
        return appointmentExpertService.update(params);
    }

    /**
     * 删除预约专家信息
     * @param id Long
     * @return ResultVo
     */
    @DeleteMapping("{id}")
    public ResultVo delete(@PathVariable("id") Long id) {
        return appointmentExpertService.delete(id);
    }

}
