package com.base.framework.admin.model.dto.appointmentDepartment;

import com.base.framework.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/9/29
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryAppointmentDepartmentQuery extends PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 科室名称
     */
    private String name;
}
