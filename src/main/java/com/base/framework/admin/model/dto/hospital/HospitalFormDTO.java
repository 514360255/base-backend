package com.base.framework.admin.model.dto.hospital;

import com.base.framework.admin.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class HospitalFormDTO extends BaseDTO implements Serializable {

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
     * 疾病类型
     */
    private String diseaseType;

}
