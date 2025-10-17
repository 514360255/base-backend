package com.base.framework.miniProgram.model.vo;

import com.base.framework.admin.model.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/10/17
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MPAppointmentExpertVO extends BaseVO {

    /**
     * 头像
     */
    private String avatar;

    /**
     * 姓名
     */
    private String name;

    /**
     * 简介
     */
    private String intro;

    /**
     * 领域
     */
    private String domain;

    /**
     * 职称
     */
    private String title;

}
