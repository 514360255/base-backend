package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.appointmentDepartment.AppointmentDepartmentDTO;
import com.base.framework.admin.model.dto.appointmentDepartment.QueryAppointmentDepartmentQuery;
import com.base.framework.admin.service.AppointmentDepartmentService;
import com.base.framework.utils.ResultVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/9/29
 * @Description:
 **/
@RestController
@RequestMapping(ADMIN_PREFIX + "appointment/department")
public class AppointmentDepartmentController {

    @Resource
    AppointmentDepartmentService appointmentDepartmentService;

    /**
     * 预约科室列表
     * @param params QueryAppointmentDepartmentDTO
     * @return Result
     */
    @GetMapping("page")
    public ResultVo queryPage(@Param("params") QueryAppointmentDepartmentQuery params) {
        return appointmentDepartmentService.queryPage(params);
    }

    /**
     * 添加预约科室
     * @param params AppointmentDepartmentDTO
     * @return Result
     */
    @PostMapping
    public ResultVo save(@RequestBody AppointmentDepartmentDTO params) {
        return appointmentDepartmentService.save(params);
    }

    /**
     * 修改预约科室
     * @param params AppointmentDepartmentDTO
     * @return Result
     */
    @PutMapping
    public ResultVo update(@RequestBody AppointmentDepartmentDTO params) {
        return appointmentDepartmentService.update(params);
    }

    @GetMapping("{id}")
    public ResultVo queryById(@PathVariable Long id) {
        return appointmentDepartmentService.queryById(id);
    }

    /**
     * 删除预约科室
     * @param id Long
     * @return Result
     */
    @DeleteMapping("{id}")
    public ResultVo deleteById(@PathVariable Long id) {
        return appointmentDepartmentService.deleteById(id);
    }

}
