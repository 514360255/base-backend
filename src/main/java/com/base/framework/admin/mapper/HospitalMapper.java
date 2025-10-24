package com.base.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.BaseDTO;
import com.base.framework.admin.model.dto.SysRoleMenuMapping;
import com.base.framework.admin.model.dto.hospital.HospitalFormDTO;
import com.base.framework.admin.model.dto.hospital.HospitalQueryDTO;
import com.base.framework.admin.model.entity.HospitalEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/8/18
 * @Description:
 **/
public interface HospitalMapper extends BaseMapper<BaseDTO> {

    /**
     * 医院列表
     * @param params HospitalQueryDTO
     * @return HospitalEntity
     */
    List<HospitalEntity> queryHospitalPage(HospitalQueryDTO params);

    /**
     * 根据code获取医院信息
     * @param code String
     * @return HospitalEntity
     */
    HospitalEntity getHospitalDetail(String code);

    /**
     * 保存医院信息
     * @param params HospitalFormDTO
     * @return Long
     */
    Long save(HospitalFormDTO params);

    /**
     * 删除医院
     * @param params HospitalFormDTO
     */
    void deleteHospital(HospitalFormDTO params);

    /**
     * 修改医院信息
     * @param params HospitalFormDTO
     */
    void updateState(HospitalFormDTO params);

    /**
     * 修改医院信息
     * @param params HospitalFormDTO
     */
    void update(HospitalFormDTO params);

    /**
     * 修改医院信息
     * @param hospitalId Long
     * @param secret String
     */
    void updateHospitalSecret(Long hospitalId, String secret);

}
