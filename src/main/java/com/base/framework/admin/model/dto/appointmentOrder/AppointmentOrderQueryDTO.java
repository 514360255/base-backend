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
     * 用户名
     */
    private String accountName;

    /**
     * 姓名
     */
    private String name;
}

