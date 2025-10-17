package com.base.framework.miniProgram.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.model.vo.AppointmentOrderVO;
import com.base.framework.miniProgram.mapper.MPAppointmentExpertMapper;
import com.base.framework.miniProgram.model.entity.MPAppointmentExpertEntity;
import com.base.framework.miniProgram.model.vo.MPAppointmentExpertVO;
import com.base.framework.miniProgram.service.MPAppointmentExpertService;
import com.base.framework.utils.ResultVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/10/17
 * @Description:
 **/
@Service
@Slf4j
public class MPAppointmentExpertServiceImpl implements MPAppointmentExpertService {

    @Resource
    MPAppointmentExpertMapper mpAppointmentExpertMapper;

    @Override
    public ResultVo queryExpertList(Long hospitalId) {
        List<MPAppointmentExpertEntity> list = mpAppointmentExpertMapper.queryExpertList(hospitalId);
        return ResultVo.ok(CglibUtil.copyList(list, MPAppointmentExpertVO::new));
    }

}
