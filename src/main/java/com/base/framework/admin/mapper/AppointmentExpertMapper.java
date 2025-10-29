package com.base.framework.admin.mapper;

import com.base.framework.admin.model.dto.appointmentExpert.AppointmentExpertQuery;
import com.base.framework.admin.model.dto.appointmentExpert.ExpertList;
import com.base.framework.admin.model.entity.AppointmentExpertEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/10/13
 * @Description:
 **/
public interface AppointmentExpertMapper {

    /**
     * 保存专家信息
     * @param hospitalId Long
     * @param accountId Long
     * @param params List<ExpertList>
     * @return Long
     */
    Long save(Long hospitalId, Long accountId, @Param("list") List<ExpertList> params);

    /**
     * 查询分页数据
     * @param params AppointmentUserQueryDTO
     * @return List<AppointmentExpertEntity>
     */
    List<AppointmentExpertEntity> queryPage(AppointmentExpertQuery params);

    /**
     * 统计总数
     * @param params AppointmentUserQueryDTO
     * @return int
     */
    int countTotal(AppointmentExpertQuery params);

    /**
     * 修改
     * @param params ExpertList
     */
    void update(ExpertList params);

    /**
     * 删除
     * @param id Long
     */
    void delete(Long id);

}
