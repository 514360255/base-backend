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
     * 账号Id
     */
    private Long accountId;

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

    /**
     * secret
     */
    private String secret;

    /**
     * appid
     */
    private String appid;

    /**
     * 医院简介图片
     */
    private String introPic;

    /**
     * 医院环境图片
     */
    private String envPic;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 额外字段
     */
    private String ext;

    /**
     * 联系电话
     */
    private String phone;
}
