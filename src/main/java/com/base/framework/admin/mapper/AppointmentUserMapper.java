package com.base.framework.admin.mapper;

import com.base.framework.admin.model.dto.appointmentUser.AppointmentUserQueryDTO;
import com.base.framework.admin.model.entity.AppointmentUserEntity;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/10/13
 * @Description:
 **/
public interface AppointmentUserMapper {

    /**
     * 查询分页数据
     * @param params AppointmentUserQueryDTO
     * @return List<AppointmentUserEntity>
     */
    List<AppointmentUserEntity> queryPage(AppointmentUserQueryDTO params);

}
