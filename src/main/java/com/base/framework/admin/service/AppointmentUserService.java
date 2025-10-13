package com.base.framework.admin.service;

import com.base.framework.admin.model.dto.appointmentUser.AppointmentUserQueryDTO;
import com.base.framework.utils.ResultVo;

/**
 * @Author: 郭郭
 * @Create: 2025/10/13
 * @Description:
 **/
public interface AppointmentUserService {

    /**
     * 查询预约用户列表
     * @param params AppointmentUserQueryDTO
     * @return ResultVo
     */
    ResultVo queryPage(AppointmentUserQueryDTO params);

}
