package com.base.framework.miniProgram.service;

import com.base.framework.utils.ResultVo;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
public interface MPHospitalService {

    /**
     * 获取医院详情
     * @param code String
     * @return ResultVo
     */
    ResultVo getHospitalDetailByCode(String code);

    /**
     * 获取医院名称
     * @param code String
     * @return ResultVo
     */
    ResultVo getNameByCode(String code);

}
