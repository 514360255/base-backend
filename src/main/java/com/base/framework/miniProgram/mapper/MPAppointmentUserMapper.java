package com.base.framework.miniProgram.mapper;

import com.base.framework.miniProgram.model.entity.MPAppointmentUserEntity;

/**
 * @Author: 郭郭
 * @Create: 2025/10/11
 * @Description:
 **/
public interface MPAppointmentUserMapper {

    /**
     * 保存
     * @param entity MPAppointmentUserEntity
     * @return Integer
     */
    Integer save(MPAppointmentUserEntity entity);

    /**
     * 根据手机号查询
     * @param mobile 手机号
     * @return MPAppointmentUserEntity
     */
    MPAppointmentUserEntity getDetailByMobile(String mobile);

    /**
     * 根据手机号查询
     * @param id Long
     * @return MPAppointmentUserEntity
     */
    MPAppointmentUserEntity getDetailById(Long id);

    /**
     * 修改
     * @param entity MPAppointmentUserEntity
     */
    void update(MPAppointmentUserEntity entity);

}
