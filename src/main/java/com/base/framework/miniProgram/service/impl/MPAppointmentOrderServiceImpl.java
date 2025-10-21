package com.base.framework.miniProgram.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.HospitalMapper;
import com.base.framework.admin.model.entity.HospitalEntity;
import com.base.framework.constant.JwtConstant;
import com.base.framework.exception.BusinessException;
import com.base.framework.miniProgram.mapper.MPAppointmentOrderMapper;
import com.base.framework.miniProgram.mapper.MPAppointmentUserMapper;
import com.base.framework.miniProgram.mapper.MPSysAccountMapper;
import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentOrderForm;
import com.base.framework.miniProgram.model.dto.appointmentOrder.MPAppointmentQueryDTO;
import com.base.framework.miniProgram.model.entity.MPAppointmentOrderEntity;
import com.base.framework.miniProgram.model.entity.MPAppointmentUserEntity;
import com.base.framework.miniProgram.model.entity.MPSysAccountEntity;
import com.base.framework.miniProgram.model.vo.MPAccountLoginVO;
import com.base.framework.miniProgram.model.vo.MPAppointmentOrderVO;
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
    public ResultVo saveAppointmentOrder(MPAppointmentOrderForm params) {

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
        params.setMobile(params.getMobile());
        Integer index = mpAppointmentOrderMapper.save(params);
        if(index == 0) {
            throw new BusinessException(500, "保存失败");
        }
        String title = hospitalEntity.getName();
        String body = "<div>" +
                "<p>姓名：" + params.getName() + "</p>" +
                "<p>年龄：" + params.getAge() + "</p>" +
                "<p>预约医生："+ params.getExpert() +"</p>" +
                "<p>电话号码：" + params.getMobile() + "</p>" +
                "<p>到院日期：" + params.getAppointmentTime() + "</p>" +
                "<p>诊疗疾病：" + params.getDisease() + "</p>" +
                "<p>疾病描述：" + params.getRemark() + "</p>" +
                "</div>";
        List<String> emails = new ArrayList<>();
        emails.addAll(Arrays.asList(sysAccount.getRecipient().split(";")));
        emails.addAll(Arrays.asList(hospitalEntity.getRecipient().split(";")));
        TencentEmailSenderMultiple.sendEmailToMultiple(title, body, emails);

        // 根据手机号查询预约用户是否存在
        MPAppointmentUserEntity appointmentUser = mpAppointmentUserMapper.getDetailByMobile(params.getMobile());
        MPAppointmentUserEntity mpAppointmentUserEntity = new MPAppointmentUserEntity();
        mpAppointmentUserEntity.setName(params.getName());
        mpAppointmentUserEntity.setAge(params.getAge());
        if(appointmentUser == null) {
            // 参数：数据中心ID（0-31），机器ID（0-31）
            Snowflake snowflake = IdUtil.createSnowflake(1, 1);
            mpAppointmentUserEntity.setId(snowflake.nextId());
            mpAppointmentUserEntity.setMobile(params.getMobile());
            mpAppointmentUserEntity.setAccountId(hospitalEntity.getAccountId());
            mpAppointmentUserMapper.save(mpAppointmentUserEntity);
        }else {
            mpAppointmentUserEntity.setId(appointmentUser.getId());
            mpAppointmentUserMapper.update(mpAppointmentUserEntity);
        }


        MPAccountLoginVO mpAccountLoginVO = new MPAccountLoginVO();
        String id = String.valueOf(mpAppointmentUserEntity.getId());
        String token = JwtTokenUtils.createToken(id);
        mpAccountLoginVO.setId(id);
        mpAccountLoginVO.setMobile(params.getMobile());
        mpAccountLoginVO.setName(params.getName());
        mpAccountLoginVO.setToken(JwtConstant.TOKEN_PREFIX +" "+ token);

        return ResultVo.ok(mpAccountLoginVO);
    }

    @Override
    public ResultVo queryAppointmentOrderList(MPAppointmentQueryDTO params) {
        List<MPAppointmentOrderEntity> list = mpAppointmentOrderMapper.queryAppointmentOrderList(params);
        return ResultVo.ok(CglibUtil.copyList(list, MPAppointmentOrderVO::new));
    }

}
