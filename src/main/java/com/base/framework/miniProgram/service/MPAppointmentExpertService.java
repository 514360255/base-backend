package com.base.framework.miniProgram.service;

import com.base.framework.utils.ResultVo;

/**
 * @Author: 郭郭
 * @Create: 2025/10/17
 * @Description:
 **/
public interface MPAppointmentExpertService {

    /**
     * 获取专家列表
     * @param hospitalId Long
     * @return ResultVo
     */
    ResultVo queryExpertList(Long hospitalId);

}
