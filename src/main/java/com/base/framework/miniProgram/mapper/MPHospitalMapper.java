package com.base.framework.miniProgram.mapper;

import com.base.framework.miniProgram.model.entity.MPHospitalEntity;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
public interface MPHospitalMapper {

    /**
     * 获取医院详情
     * @param code String
     * @return
     */
    MPHospitalEntity getHospitalDetailByCode(String code);

    /**
     * 获取医院详情
     * @param code String
     * @return MPHospitalMapper
     */
    MPHospitalEntity getDetailByCode(String code);

    /**
     * 保存授权次数
     * @param hospitalId Long
     */
    void saveAuthNumber(Long hospitalId);

}
