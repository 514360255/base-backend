package com.base.framework.miniProgram.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.miniProgram.mapper.MPDepartmentMapper;
import com.base.framework.miniProgram.mapper.MPHospitalMapper;
import com.base.framework.miniProgram.model.entity.MPDepartmentEntity;
import com.base.framework.miniProgram.model.entity.MPHospitalEntity;
import com.base.framework.miniProgram.model.vo.MPDepartmentVO;
import com.base.framework.miniProgram.model.vo.MPHospitalVO;
import com.base.framework.miniProgram.service.MPHospitalService;
import com.base.framework.utils.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
@Service
@Slf4j
public class MPHospitalServiceImpl implements MPHospitalService {

    @Resource
    MPHospitalMapper mpHospitalMapper;

    @Resource
    MPDepartmentMapper mpDepartmentMapper;

    @Override
    public ResultVo getHospitalDetailByCode(String code) {
        MPHospitalEntity mpHospitalEntity = mpHospitalMapper.getHospitalDetailByCode(code);
        MPHospitalVO mpHospitalVO = new MPHospitalVO();
        List<Long> departmentIds = Arrays.stream(mpHospitalEntity.getDepartmentIds().split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        List<MPDepartmentEntity> mpDepartmentEntities = mpDepartmentMapper.queryDepartmentList(departmentIds);
        BeanUtils.copyProperties(mpHospitalEntity, mpHospitalVO);
        mpHospitalVO.setDepartmentList(CglibUtil.copyList(mpDepartmentEntities, MPDepartmentVO::new));
        return ResultVo.ok(mpHospitalVO);
    }

}
