package com.base.framework.admin.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.AppointmentUserMapper;
import com.base.framework.admin.model.dto.appointmentUser.AppointmentUserQueryDTO;
import com.base.framework.admin.model.entity.AppointmentUserEntity;
import com.base.framework.admin.model.vo.AppointmentUserVO;
import com.base.framework.admin.service.AppointmentUserService;
import com.base.framework.utils.ResultVo;
import com.base.framework.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/10/13
 * @Description:
 **/
@Service
@Slf4j
public class AppointmentUserServiceImpl implements AppointmentUserService {

    @Resource
    AppointmentUserMapper appointmentUserMapper;

    @Override
    public ResultVo queryPage(AppointmentUserQueryDTO params) {
        if(!SecurityUtils.isSuperAdmin()) {
            params.setAccountId(SecurityUtils.getCurrentUserId());
        }
        int total = appointmentUserMapper.countTotal(params);
        List<AppointmentUserEntity> list = PageHelper.startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                .doSelectPage(() -> appointmentUserMapper.queryPage(params));

        PageInfo<AppointmentUserVO> pageInfo = new PageInfo<>(CglibUtil.copyList(list, AppointmentUserVO::new));
        pageInfo.setTotal(total);
        return ResultVo.ok(pageInfo);
    }
}
