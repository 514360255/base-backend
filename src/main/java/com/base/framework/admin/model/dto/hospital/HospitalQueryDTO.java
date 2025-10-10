package com.base.framework.admin.model.dto.hospital;

import com.base.framework.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class HospitalQueryDTO extends PageRequest implements Serializable {

    /**
     * 医院名称
     */
    private String name;

    /**
     * 状态
     */
    private Long isActive;

    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 科室id
     */
    private Long departmentId;

}
