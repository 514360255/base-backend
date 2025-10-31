package com.base.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.BaseDTO;
import com.base.framework.admin.model.dto.appointmentOrder.AppointmentOrderQueryDTO;
import com.base.framework.admin.model.entity.AppointmentOrderEntity;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
public interface AppointmentOrderMapper extends BaseMapper<BaseDTO> {

    /**
     * 保存预约单
     * @param params AppointmentOrderQueryDTO
     * @return List<AppointmentOrderEntity>
     */
    List<AppointmentOrderEntity> queryPage(AppointmentOrderQueryDTO params);

    /**
     * 统计总数
     * @param params AppointmentOrderQueryDTO
     * @return int
     */
    int countTotal(AppointmentOrderQueryDTO params);

    /**
     * 是否已到诊
     * @param id Long
     * @param isVisit int
     */
    void hasVisit(Long id, int isVisit);

    /**
     * 获取详情
     * @param id Long
     * @return AppointmentOrderEntity
     */
    AppointmentOrderEntity getDetailById(Long id);

    /**
     * 删除
     * @param id Long
     * @param username String
     */
    void deleteById(Long id, String username);

}
