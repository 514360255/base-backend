package com.base.framework.miniProgram.model.entity;

import lombok.Data;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
@Data
public class MPHospitalEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 医院名称
     */
    private String name;

    /**
     * 医院CODE
     */
    private String code;

    /**
     * 医院描述
     */
    private String description;

    /**
     * 医院地址
     */
    private String address;

    /**
     * 医院特色
     */
    private String feature;

    /**
     * 医院科室id
     */
    private String departmentIds;

    /**
     * 接诊时间
     */
    private String consultationHours;
}
