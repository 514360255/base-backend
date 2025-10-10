package com.base.framework.admin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/9/29
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AppointmentDepartmentEntity extends BaseEntity {

    /**
     * 科室名
     */
    private String name;

    /**
     * 显示名
     */
    private String title;

    /**
     * banner 图片
     */
    private String bannerUrl;

    /**
     * 问题列表
     */
    private String problems;

    /**
     * 疾病类型
     */
    private String diseaseType;

    /**
     * 诊疗项目
     */
    private String diagnosisItems;

}
