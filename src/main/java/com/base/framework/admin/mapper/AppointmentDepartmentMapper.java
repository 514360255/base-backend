package com.base.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.BaseDTO;
import com.base.framework.admin.model.dto.appointmentDepartment.AppointmentDepartmentDTO;
import com.base.framework.admin.model.dto.appointmentDepartment.QueryAppointmentDepartmentQuery;
import com.base.framework.admin.model.entity.AppointmentDepartmentEntity;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/29
 * @Description:
 **/
public interface AppointmentDepartmentMapper extends BaseMapper<BaseDTO> {

    /**
     * 预约科室列表
     * @param params QueryAppointmentDepartmentDTO
     * @return List<AppointmentOrderEntity>
     */
    List<AppointmentDepartmentEntity> queryPage(QueryAppointmentDepartmentQuery params);

    /**
     * 保存预约科室
     * @param params AppointmentOrderEntity
     * @return Integer
     */
    Integer save(AppointmentDepartmentDTO params);

    /**
     * 根据名称查询预约科室
     * @param name String
     * @return AppointmentOrderEntity
     */
    Integer getDetailByName(String name);

    /**
     * 修改预约科室
     * @param params AppointmentDepartmentDTO
     */
    void update(AppointmentDepartmentDTO params);

    /**
     * 修改预约科室
     * @param id Integer
     * @return Integer
     */
    AppointmentDepartmentEntity getDetailById(Long id);

    /**
     * 删除预约科室
     * @param id Long
     */
    void deleteById(Long id);

}
