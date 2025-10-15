package com.base.framework.miniProgram.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
@Data
public class MPHospitalVO {

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
     * 科室列表
     */
    private List<MPDepartmentVO> departmentList;

    /**
     * 接诊时间
     */
    private String consultationHours;

    /**
     * 医院简介图片
     */
    private String introPic;

    /**
     * 医院环境图片
     */
    private String envPic;
}
