package com.base.framework.miniProgram.service.impl;

import com.base.framework.admin.mapper.AccountMapper;
import com.base.framework.admin.mapper.HospitalMapper;
import com.base.framework.admin.model.entity.HospitalEntity;
import com.base.framework.admin.model.entity.SysAccount;
import com.base.framework.exception.BusinessException;
import com.base.framework.miniProgram.mapper.MPAppointmentOrderMapper;
import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentOrderForm;
import com.base.framework.miniProgram.service.MPAppointmentOrderService;
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
    HospitalMapper hospitalMapper;

    @Resource
    AccountMapper accountMapper;

    @Override
    @Transactional
    public ResultVo<Long> saveAppointmentOrder(MPAppointmentOrderForm params) {
        HospitalEntity hospitalEntity = hospitalMapper.getHospitalDetail(params.getHospitalId());
        if(hospitalEntity == null) {
            throw new BusinessException(500, "医院不存在");
        }
        SysAccount sysAccount = accountMapper.getUserInfoById(hospitalEntity.getAccountId());
        if(sysAccount == null) {
            throw new BusinessException(500, "关联用户不存在");
        }
        params.setAccountId(sysAccount.getId());
        params.setHospitalName(hospitalEntity.getName());
        params.setAccountName(sysAccount.getName());
        Integer index = mpAppointmentOrderMapper.save(params);
        if(index == 0) {
            throw new BusinessException(500, "保存失败");
        }
        String title = hospitalEntity.getName();
        String body = "<div>" +
                "<p>姓名：" + params.getName() + "</p>" +
                "<p>年龄：" + params.getAge() + "</p>" +
                "<p>电话号码：" + params.getMobile() + "</p>" +
                "<p>到院日期：" + params.getAppointmentTime() + "</p>" +
                "<p>诊疗疾病：" + params.getDisease() + "</p>" +
                "<p>疾病描述：" + params.getRemark() + "</p>" +
                "</div>";
        List<String> emails = new ArrayList<>(Arrays.asList(sysAccount.getRecipient().split(";")));
        TencentEmailSenderMultiple.sendEmailToMultiple(title, body, emails);
        return ResultVo.ok(params.getId());
    }

}
