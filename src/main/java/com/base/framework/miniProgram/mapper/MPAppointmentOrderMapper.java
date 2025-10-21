package com.base.framework.miniProgram.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.BaseDTO;
import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentOrderForm;
import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentQueryDTO;
import com.base.framework.miniProgram.model.entity.MPAppointmentOrderEntity;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
public interface MPAppointmentOrderMapper extends BaseMapper<BaseDTO> {

    /**
     * 保存预约单
     * @param params AppointmentOrderForm
     * @return Integer
     */
    Integer save(MPAppointmentOrderForm params);

    /**
     * 查询预约单列表
     * @param params AppointmentQueryDTO
     * @return List<MPAppointmentOrderEntity>
     */
    List<MPAppointmentOrderEntity> queryAppointmentOrderList(MPAppointmentQueryDTO params);

}
