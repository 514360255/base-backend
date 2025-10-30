package com.base.framework.admin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/9/4
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AppointmentOrderEntity extends BaseEntity {

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
     * 医院名称
     */
    private String hospitalName;

    /**
     * 所属人
     */
    private String accountName;

    /**
     * 核销码
     */
    private Integer writeOff;

    /**
     * 是否到诊
     */
    private Integer isVisit;

    /**
     * 专家
     */
    private String expert;

}
