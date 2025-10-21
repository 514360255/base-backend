package com.base.framework.miniProgram.service;

import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentOrderForm;
import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentQueryDTO;
import com.base.framework.utils.ResultVo;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
public interface MPAppointmentOrderService {

    /**
     * 预约订单保存
     * @param params AppointmentOrderForm
     * @return ResultVo<Long>
     */
    ResultVo saveAppointmentOrder(MPAppointmentOrderForm params);

    /**
     * 预约订单列表
     * @param params
     * @return
     */
    ResultVo queryAppointmentOrderList(MPAppointmentQueryDTO params);

}
