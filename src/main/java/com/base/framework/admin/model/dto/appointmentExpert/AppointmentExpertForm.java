package com.base.framework.admin.model.dto.appointmentExpert;

import com.base.framework.admin.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/4
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AppointmentExpertForm extends BaseDTO {

    /**
     * 医院ID
     */
    private Long hospitalId;

    /**
     * 账号ID
     */
    private Long accountId;

    /**
     * 专家列表
     */
    List<ExpertList> expertList;


}
