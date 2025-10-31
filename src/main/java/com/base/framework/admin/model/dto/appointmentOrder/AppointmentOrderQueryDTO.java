package com.base.framework.admin.model.dto.appointmentOrder;

import com.base.framework.admin.model.dto.BaseDTO;
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
public class AppointmentOrderQueryDTO  extends PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 医院名称
     */
    private String hospitalName;

    /**
     * 医院id
     */
    private Long hospitalId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 姓名
     */
    private Long accountId;

    /**
     * 是否到诊
     */
    private Integer isVisit;
}

