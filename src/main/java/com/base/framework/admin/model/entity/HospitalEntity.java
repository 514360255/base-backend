package com.base.framework.admin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class HospitalEntity extends BaseEntity {

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
     * 医院科室名称
     */
    private String departmentNames;

    /**
     * appid
     */
    private String appid;

    /**
     * secret
     */
    private String secret;

    /**
     * 接诊时间
     */
    private String consultationHours;

    /**
     * 所属人
     */
    private String accountName;

    /**
     * 授权次数
     */
    private Integer authNumber;
}
