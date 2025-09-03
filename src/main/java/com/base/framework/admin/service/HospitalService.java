package com.base.framework.admin.service;

import com.base.framework.admin.model.dto.hospital.HospitalFormDTO;
import com.base.framework.admin.model.dto.hospital.HospitalQueryDTO;
import com.base.framework.admin.model.entity.HospitalEntity;
import com.base.framework.admin.model.vo.HospitalVO;
import com.base.framework.utils.ResultVo;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
public interface HospitalService {

    /**
     * 医院列表
     * @param params
     * @return
     */
    ResultVo queryHospitalPage(HospitalQueryDTO params);

    /**
     * 保存医院信息
     * @param params HospitalFormDTO
     * @return ResultVo<Long>
     */
    ResultVo<Long> save(HospitalFormDTO params);

    /**
     * 保存医院信息
     * @param input String
     * @return HospitalVO
     */
    HospitalVO getHospitalDetail(String input);

    /**
     * 删除医院
     * @param id Long
     * @return ResultVo
     */
    ResultVo deleteHospital(Long id);

    /**
     * 修改状态
     * @param params HospitalFormDTO
     * @return ResultVo<Boolean>
     */
    ResultVo<Boolean> updateState(HospitalFormDTO params);

    /**
     * 修改状态
     * @param params HospitalFormDTO
     * @return ResultVo<Boolean>
     */
    ResultVo<Boolean> update(HospitalFormDTO params);

}
