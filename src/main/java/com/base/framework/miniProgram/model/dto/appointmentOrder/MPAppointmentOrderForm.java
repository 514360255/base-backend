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
public class MPAppointmentOrderForm extends BaseDTO implements Serializable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private  String mobile;

    /**
     * 预约时间
     */
    private String appointmentTime;

    /**
     * 疾病类型
     */
    private String disease;

    /**
     * 预约信息
     */
    private String remark;

    /**
     * 医院id
     */
    private String hospitalId;

    /**
     * 医院名
     */
    private String hospitalName;

    /**
     * 用户名
     */
    private String accountName;

    /**
     * 核销码
     */
    private Integer writeOff;

    /**
     * 专家
     */
    private String expert;
}
