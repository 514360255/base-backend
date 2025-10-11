package com.base.framework.admin.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.HospitalMapper;
import com.base.framework.admin.model.dto.hospital.HospitalFormDTO;
import com.base.framework.admin.model.dto.hospital.HospitalQueryDTO;
import com.base.framework.admin.model.entity.HospitalEntity;
import com.base.framework.admin.model.vo.HospitalVO;
import com.base.framework.admin.service.HospitalService;
import com.base.framework.exception.BusinessException;
import com.base.framework.utils.AesEncryptionUtil;
import com.base.framework.utils.ResultVo;
import com.base.framework.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@Service
@Slf4j
public class HospitalServiceImpl implements HospitalService {

    @Resource
    HospitalMapper hospitalMapper;

    @Override
    public ResultVo queryHospitalPage(HospitalQueryDTO params) {
        String roleCode = SecurityUtils.getRoleCode();
        if(!"SUPER_ADMIN".equals(roleCode)) {
            params.setAccountId(SecurityUtils.getCurrentUserId());
        }
        List<HospitalEntity> list =
                PageHelper.startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                        .doSelectPage(() -> hospitalMapper.queryHospitalPage(params));
        return ResultVo.ok(new PageInfo<>(CglibUtil.copyList(list, HospitalVO::new)));
    }

    @Override
    public HospitalVO getHospitalDetail(String input) {
        HospitalEntity hospitalEntity = hospitalMapper.getHospitalDetail(input);
        if(hospitalEntity == null) {
            throw new BusinessException(500, "医院不存在");
        }
        HospitalVO hospitalVO = new HospitalVO();
        BeanUtils.copyProperties(hospitalEntity, hospitalVO);
        return hospitalVO;
    }

    @Override
    @Transactional
    public ResultVo<Long> save(HospitalFormDTO params)  {
        HospitalEntity hospitalEntity = hospitalMapper.getHospitalDetail(params.getCode());
        if(hospitalEntity != null) {
            throw new BusinessException(500, "医院code重复");
        }
        if(params.getAppid() == null) {
            throw new BusinessException(500, "appid不能为空");
        }
        if(params.getSecret() == null) {
            throw new BusinessException(500, "secret不能为空");
        }
        String appid = AesEncryptionUtil.encrypt(params.getAppid());
        String secret = AesEncryptionUtil.encrypt(params.getSecret());
        params.setAppid(appid);
        params.setSecret(secret);
        hospitalMapper.save(params);
        return ResultVo.ok(params.getId());
    }

    @Override
    @Transactional
    public ResultVo<Boolean> deleteHospital(Long id) {
        this.getHospitalDetail(String.valueOf(id));
        HospitalFormDTO hospitalFormDTO = new HospitalFormDTO();
        hospitalFormDTO.setId(id);
        hospitalFormDTO.setCreatedBy(SecurityUtils.getCurrentUsername());
        hospitalMapper.deleteHospital(hospitalFormDTO);
        return ResultVo.ok(true);
    }

    @Override
    @Transactional
    public ResultVo<Boolean> updateState(HospitalFormDTO params) {
        this.getHospitalDetail(String.valueOf(params.getId()));
        hospitalMapper.updateState(params);
        return ResultVo.ok(true);
    }

    @Override
    @Transactional
    public ResultVo<Boolean> update(HospitalFormDTO params) {
        this.getHospitalDetail(String.valueOf(params.getId()));
        hospitalMapper.update(params);
//        appointmentHospitalDepartmentMappingMapper.delete(params.getId());
//        List<AppointmentHospitalDepartmentMappingForm> list = new ArrayList<>();
//        if(params.getDepartmentIds() == null || params.getDepartmentIds().isEmpty()) {
//            throw new BusinessException(500, "科室id不能为空");
//        }
//        params.getDepartmentIds().forEach(item -> {
//            AppointmentHospitalDepartmentMappingForm mapping = new AppointmentHospitalDepartmentMappingForm();
//            mapping.setHospitalId(params.getId());
//            mapping.setDepartmentId(item);
//            list.add(mapping);
//        });
//        appointmentHospitalDepartmentMappingMapper.save(list);
        return ResultVo.ok(true);
    }

}
