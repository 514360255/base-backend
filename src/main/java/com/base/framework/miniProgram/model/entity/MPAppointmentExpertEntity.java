package com.base.framework.miniProgram.model.entity;

import com.base.framework.admin.model.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/10/17
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MPAppointmentExpertEntity extends BaseEntity {

    /**
     * 头像
     */
    private String avatar;

    /**
     * 姓名
     */
    private String name;

    /**
     * 简介
     */
    private String intro;

    /**
     * 领域
     */
    private String domain;

    /**
     * 职称
     */
    private String title;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 医生预约时间
     */
    private String consultationHours;

}
