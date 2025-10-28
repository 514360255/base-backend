package com.base.framework.miniProgram.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.base.framework.miniProgram.mapper.MPSysAccountMapper;
import com.base.framework.miniProgram.model.entity.MPSysAccountEntity;
import com.base.framework.miniProgram.model.vo.MPAccountLoginVO;
import com.base.framework.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.base.framework.constant.JwtConstant;
import com.base.framework.exception.BusinessException;
import com.base.framework.miniProgram.mapper.MPAppointmentUserMapper;
import com.base.framework.miniProgram.mapper.MPHospitalMapper;
import com.base.framework.miniProgram.model.dto.account.MPAuthForm;
import com.base.framework.miniProgram.model.dto.account.WxLoginResponse;
import com.base.framework.miniProgram.model.entity.MPAppointmentUserEntity;
import com.base.framework.miniProgram.model.entity.MPHospitalEntity;
import com.base.framework.miniProgram.service.MPAccountService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Resource
    MPAppointmentUserMapper mpAppointmentUserMapper;

    @Resource
    private MPSysAccountMapper mpSysAccountMapper;

    @Autowired
    private RestTemplate restTemplate;

    private static final String CODE_TO_SESSION_URL =
            "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Override
    public ResultVo phoneAuth(MPAuthForm params) {
        if (params.getIv() == null || params.getCode() == null || params.getEncryptedData() == null || params.getHospitalCode() == null) {
            throw new BusinessException(500, "缺少参数");
        }

        try {
            MPHospitalEntity mpHospitalEntity = mpHospitalMapper.getDetailByCode(params.getHospitalCode());
            String appid = AesEncryptionUtil.decrypt(mpHospitalEntity.getAppid());
            String url = String.format(
                    CODE_TO_SESSION_URL,
                    appid,
                    AesEncryptionUtil.decrypt(mpHospitalEntity.getSecret()),
                    params.getCode()
            );

            WxLoginResponse sessionResponse = restTemplate.getForObject(url, WxLoginResponse.class);

            if (sessionResponse == null || sessionResponse.getErrcode() != null && sessionResponse.getErrcode() != 0) {
                throw new BusinessException(500, "微信接口错误: " + sessionResponse.getErrmsg());
            }

            String sessionKey = sessionResponse.getSession_key();
            String decryptedData = WxPhoneDecryptorUtil.decryptPhone(params.getEncryptedData(), params.getIv(), sessionKey);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(decryptedData);

            // 校验 watermark
            JsonNode watermark = rootNode.get("watermark");

            if (watermark == null || !appid.equals(watermark.get("appid").asText())) {
                throw new BusinessException(500, "无效的数据来源");
            }

            String phoneNumber = rootNode.get("phoneNumber").asText();

            // 更新授权次数
            mpHospitalMapper.saveAuthNumber(mpHospitalEntity.getId());

            // 根据手机号获取用户信息
            MPAppointmentUserEntity user = mpAppointmentUserMapper.getDetailByMobile(phoneNumber);
            MPAppointmentUserEntity mpAppointmentUserEntity = new MPAppointmentUserEntity();

            if(user == null) {
                // 新增预约用户
                // 参数：数据中心ID（0-31），机器ID（0-31）
                Snowflake snowflake = IdUtil.createSnowflake(1, 1);
                mpAppointmentUserEntity.setAccountId(mpHospitalEntity.getAccountId());
                mpAppointmentUserEntity.setMobile(phoneNumber);
                mpAppointmentUserEntity.setId(snowflake.nextId());
                mpAppointmentUserMapper.save(mpAppointmentUserEntity);
            }else {
                mpAppointmentUserEntity.setId(user.getId());
            }

            // 发送邮件
            MPSysAccountEntity mpSysAccountEntity = mpSysAccountMapper.getDetailByAccountId(mpHospitalEntity.getAccountId());
            String title = mpHospitalEntity.getName();
            String body = "<div>" +
                    "<p>电话号码：" + phoneNumber + "</p>" +
                    "</div>";
            List<String> emails = new ArrayList<>(Arrays.asList(mpSysAccountEntity.getRecipient().split(";")));
            if(mpHospitalEntity.getRecipient() != null) {
                emails.addAll(Arrays.asList(mpHospitalEntity.getRecipient().split(";")));
            }
            new TencentEmailSenderMultiple().sendEmailToMultiple(title, body, emails);

            MPAccountLoginVO mpAccountLoginVO = new MPAccountLoginVO();
            String id = String.valueOf(mpAppointmentUserEntity.getId());
            String token = JwtTokenUtils.createToken(id);
            mpAccountLoginVO.setId(id);
            mpAccountLoginVO.setMobile(phoneNumber);
            mpAccountLoginVO.setToken(JwtConstant.TOKEN_PREFIX +" "+ token);
            if(user != null) {
                mpAccountLoginVO.setName(user.getName());
                mpAccountLoginVO.setAge(user.getAge());
            }

            return ResultVo.ok(mpAccountLoginVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(500, "解密失败: " + e.getMessage());
        }
    }

}
