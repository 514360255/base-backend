package com.base.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.BaseDTO;
import com.base.framework.admin.model.dto.appointmentDepartment.AppointmentHospitalDepartmentMappingForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/29
 * @Description:
 **/
public interface AppointmentHospitalDepartmentMappingMapper extends BaseMapper<BaseDTO> {

    /**
     * 保存
     * @param list List<AppointmentHospitalDepartmentMappingForm>
     * @return int
     */
    int save(@Param("list") List<AppointmentHospitalDepartmentMappingForm> list);

    /**
     * 删除
     * @param hospitalId Long
     */
    void delete(Long hospitalId);

}
