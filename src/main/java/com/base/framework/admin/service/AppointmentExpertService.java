package com.base.framework.admin.service;

import com.base.framework.admin.model.dto.appointmentExpert.AppointmentExpertForm;
import com.base.framework.admin.model.dto.appointmentExpert.AppointmentExpertQuery;
import com.base.framework.admin.model.dto.appointmentExpert.ExpertList;
import com.base.framework.admin.model.dto.appointmentUser.AppointmentUserQueryDTO;
import com.base.framework.utils.ResultVo;

/**
 * @Author: 郭郭
 * @Create: 2025/10/13
 * @Description:
 **/
public interface AppointmentExpertService {

    /**
     * 查询预约用户列表
     * @param params AppointmentExpertQuery
     * @return ResultVo
     */
    ResultVo queryPage(AppointmentExpertQuery params);

    /**
     * 保存预约用户
     * @param params AppointmentExpertForm
     * @return ResultVo
     */
    ResultVo save(AppointmentExpertForm params);

    /**
     * 修改预约用户
     * @param params AppointmentExpertForm
     * @return ResultVo
     */
    ResultVo update(ExpertList params);

    /**
     *  删除
     * @param id Long
     * @return ResultVo
     */
    ResultVo delete(Long id);

}
