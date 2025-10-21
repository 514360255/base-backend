package com.base.framework.miniProgram.model.vo;

import com.base.framework.admin.model.entity.BaseEntity;
import com.base.framework.admin.model.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 郭郭
 * @Create: 2025/9/4
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MPAppointmentOrderVO extends BaseVO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private  String mobile;

    /**
     * 预约时间
     */
    private String appointmentTime;

    /**
     * 疾病类型
     */
    private String disease;

    /**
     * 预约信息
     */
    private String remark;

    /**
     * 专家
     */
    private String expert;

}
