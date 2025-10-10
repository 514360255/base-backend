package com.base.framework.miniProgram.model.entity;

import lombok.Data;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
@Data
public class MPDepartmentEntity {

    /**
     * id
     */
    private Long id;

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
