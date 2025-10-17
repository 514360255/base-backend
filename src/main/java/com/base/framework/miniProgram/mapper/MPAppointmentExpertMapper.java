package com.base.framework.miniProgram.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.BaseDTO;
import com.base.framework.miniProgram.model.entity.MPAppointmentExpertEntity;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/10/17
 * @Description:
 **/
public interface MPAppointmentExpertMapper extends BaseMapper<BaseDTO> {

    /**
     * 获取专家列表
     * @param hospitalId Long
     * @return List<MPAppointmentExpertEntity>
     */
    List<MPAppointmentExpertEntity> queryExpertList(Long hospitalId);

}
