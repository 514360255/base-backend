package com.base.framework.admin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/9/4
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AppointmentOrderEntity extends BaseEntity {

    /**
     * 医院id
     */
    private Long hospitalId;

    /**
     * 标题
     */
    private String title;

}
