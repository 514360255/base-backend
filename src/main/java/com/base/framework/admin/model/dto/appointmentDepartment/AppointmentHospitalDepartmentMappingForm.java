package com.base.framework.admin.model.dto.appointmentDepartment;

import com.base.framework.admin.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/10/9
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AppointmentHospitalDepartmentMappingForm extends BaseDTO {
    /**
     * 预约医院id
     */
    private Long hospitalId;

    /**
     * 预约科室id
     */
    private Long departmentId;

}
