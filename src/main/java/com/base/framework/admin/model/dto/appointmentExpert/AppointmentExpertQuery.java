package com.base.framework.admin.model.dto.appointmentExpert;

import com.base.framework.common.PageRequest;
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
public class AppointmentExpertQuery extends PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 医院Id
     */
    private Long hospitalId;

    /**
     * 账号Id
     */
    private Long accountId;
}

