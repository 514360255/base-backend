package com.base.framework.miniProgram.service.impl;

import com.base.framework.miniProgram.mapper.MPHospitalMapper;
import com.base.framework.miniProgram.model.dto.account.AuthForm;
import com.base.framework.miniProgram.model.entity.MPHospitalEntity;
import com.base.framework.miniProgram.service.MPAccountService;
import com.base.framework.utils.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
@Service
@Slf4j
public class MPAccountServiceImpl implements MPAccountService {

    @Resource
    MPHospitalMapper mpHospitalMapper;

    @Override
    public ResultVo login(AuthForm params) {

        MPHospitalEntity mpHospitalEntity = mpHospitalMapper.getDetail(params.getCode());

        return ResultVo.ok("");
    }

}
