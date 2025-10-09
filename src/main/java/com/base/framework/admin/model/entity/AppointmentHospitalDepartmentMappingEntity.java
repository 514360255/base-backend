package com.base.framework.admin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/9/29
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AppointmentHospitalDepartmentMappingEntity extends BaseEntity {

    /**
     * 医院id
     */
    private Long hospitalId;

    /**
     * 科室id
     */
    private Long departmentId;


}
