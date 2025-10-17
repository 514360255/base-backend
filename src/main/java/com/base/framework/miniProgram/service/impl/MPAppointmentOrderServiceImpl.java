package com.base.framework.miniProgram.service.impl;

import com.base.framework.admin.mapper.HospitalMapper;
import com.base.framework.admin.model.entity.HospitalEntity;
import com.base.framework.exception.BusinessException;
import com.base.framework.miniProgram.mapper.MPAppointmentOrderMapper;
import com.base.framework.miniProgram.mapper.MPAppointmentUserMapper;
import com.base.framework.miniProgram.mapper.MPSysAccountMapper;
import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentOrderForm;
import com.base.framework.miniProgram.model.entity.MPAppointmentUserEntity;
import com.base.framework.miniProgram.model.entity.MPSysAccountEntity;
import com.base.framework.miniProgram.service.MPAppointmentOrderService;
import com.base.framework.utils.JwtTokenUtils;
import com.base.framework.utils.ResultVo;
import com.base.framework.utils.TencentEmailSenderMultiple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@Service
@Slf4j
public class MPAppointmentOrderServiceImpl implements MPAppointmentOrderService {

    @Resource
    MPAppointmentOrderMapper mpAppointmentOrderMapper;

    @Resource
    MPAppointmentUserMapper mpAppointmentUserMapper;

    @Resource
    HospitalMapper hospitalMapper;

    @Resource
    MPSysAccountMapper mpSysAccountMapper;

    @Override
    @Transactional
    public ResultVo<Long> saveAppointmentOrder(MPAppointmentOrderForm params, String auth) {

        if(auth == null) {
            throw new BusinessException(401, "token过期，请登录");
        }
        Long userId = JwtTokenUtils.getUserId(auth);
        if(userId == null) {
            throw new BusinessException(401, "用户不存在或token过期，请登录");
        }
        MPAppointmentUserEntity appointmentUser = mpAppointmentUserMapper.getDetailById(userId);

        HospitalEntity hospitalEntity = hospitalMapper.getHospitalDetail(params.getHospitalId());
        if(hospitalEntity == null) {
            throw new BusinessException(500, "医院不存在");
        }
        MPSysAccountEntity sysAccount = mpSysAccountMapper.getDetailByAccountId(hospitalEntity.getAccountId());
        if(sysAccount == null) {
            throw new BusinessException(500, "关联用户不存在");
        }
        params.setAccountId(sysAccount.getId());
        params.setHospitalName(hospitalEntity.getName());
        params.setAccountName(sysAccount.getName());
        params.setMobile(appointmentUser.getMobile());
        Integer index = mpAppointmentOrderMapper.save(params);
        if(index == 0) {
            throw new BusinessException(500, "保存失败");
        }
        String title = hospitalEntity.getName();
        String body = "<div>" +
                "<p>姓名：" + params.getName() + "</p>" +
                "<p>年龄：" + params.getAge() + "</p>" +
                "<p>专家：专家</p>" +
                "<p>电话号码：" + appointmentUser.getMobile() + "</p>" +
                "<p>到院日期：" + params.getAppointmentTime() + "</p>" +
                "<p>诊疗疾病：" + params.getDisease() + "</p>" +
                "<p>疾病描述：" + params.getRemark() + "</p>" +
                "</div>";
        List<String> emails = new ArrayList<>();
        emails.addAll(Arrays.asList(sysAccount.getRecipient().split(";")));
        emails.addAll(Arrays.asList(hospitalEntity.getRecipient().split(";")));
        TencentEmailSenderMultiple.sendEmailToMultiple(title, body, emails);

        MPAppointmentUserEntity mpAppointmentUserEntity = new MPAppointmentUserEntity();
        mpAppointmentUserEntity.setName(params.getName());
        mpAppointmentUserEntity.setAge(params.getAge());
        mpAppointmentUserEntity.setId(userId);
        mpAppointmentUserMapper.update(mpAppointmentUserEntity);

        return ResultVo.ok(params.getId());
    }

}
