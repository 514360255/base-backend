package com.base.framework.admin.model.dto.appointmentExpert;

import com.base.framework.admin.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/10/16
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ExpertList extends BaseDTO {
    /**
     * 专家头像
     */
    private String avatar;
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

    /**
     * 排序
     */
    private  Integer sortOrder;

    /**
     * 预约时间
     */
    private  String consultationHours;
}
