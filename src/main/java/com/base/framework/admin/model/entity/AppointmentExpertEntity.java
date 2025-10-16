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
public class AppointmentExpertEntity extends BaseEntity {

    /**
     * 医院ID
     */
    private Long hospitalId;

    /**
     * 医院名称
     */
    private String hospitalName;

    /**
     * 专家头像
     */
    private String avatar;

    /**
     * 账号名
     */
    private String accountName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 介绍
     */
    private  String intro;

    /**
     * 领域
     */
    private  String domain;

    /**
     * 职称
     */
    private  String title;


}
