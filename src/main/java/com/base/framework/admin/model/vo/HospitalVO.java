package com.base.framework.admin.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class HospitalVO extends BaseVO {
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
     * 医院科室id
     */
    private String departmentId;

    /**
     * 医院科室名称
     */
    private String department;

    /**
     * 账号id
     */
    private String accountId;
}
