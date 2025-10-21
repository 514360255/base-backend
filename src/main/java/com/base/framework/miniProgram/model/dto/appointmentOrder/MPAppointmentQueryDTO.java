package com.base.framework.miniProgram.model.dto.appointmentOrder;

import com.base.framework.admin.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MPAppointmentQueryDTO extends BaseDTO implements Serializable {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 医院id
     */
    private Long hospitalId;

}
