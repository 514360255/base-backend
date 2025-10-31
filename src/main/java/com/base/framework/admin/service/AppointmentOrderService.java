package com.base.framework.admin.service;

import com.base.framework.admin.model.dto.appointmentOrder.AppointmentOrderQueryDTO;
import com.base.framework.utils.ResultVo;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
public interface AppointmentOrderService {

    /**
     * 预约订单列表
     * @param params AppointmentOrderQueryDTO
     * @return ResultVo
     */
    ResultVo queryPage(AppointmentOrderQueryDTO params);

    /**
     * 是否已看诊
     * @param id Long
     * @param isVisit int
     * @return ResultVo
     */
    ResultVo hasVisit(Long id, int isVisit);

    /**
     * 删除
     * @param id Long
     * @return ResultVo
     */
    ResultVo delete(Long id);

}
