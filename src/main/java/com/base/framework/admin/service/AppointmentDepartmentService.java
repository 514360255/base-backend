package com.base.framework.admin.service;

import com.base.framework.admin.model.dto.appointmentDepartment.AppointmentDepartmentDTO;
import com.base.framework.admin.model.dto.appointmentDepartment.QueryAppointmentDepartmentQuery;
import com.base.framework.utils.ResultVo;

/**
 * @Author: 郭郭
 * @Create: 2025/9/29
 * @Description:
 **/
public interface AppointmentDepartmentService {

    /**
     * 预约科室列表
     * @param params QueryAppointmentDepartmentDTO
     * @return Result
     */
    ResultVo queryPage(QueryAppointmentDepartmentQuery params);

    /**
     * 预约科室保存
     * @param params AppointmentDepartmentDTO
     * @return Result
     */
    ResultVo save(AppointmentDepartmentDTO params);

    /**
     * 预约科室修改
     * @param params AppointmentDepartmentDTO
     * @return Result
     */
    ResultVo update(AppointmentDepartmentDTO params);

    /**
     * 预约科室详情
     * @param id Long
     * @return ResultVo
     */
    ResultVo queryById(Long id);

    /**
     * 删除预约科室
     * @param id Long
     * @return ResultVo
     */
    ResultVo deleteById(Long id);

}
