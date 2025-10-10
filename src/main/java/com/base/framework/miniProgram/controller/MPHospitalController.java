package com.base.framework.miniProgram.controller;

import com.base.framework.miniProgram.service.MPHospitalService;
import com.base.framework.utils.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.MINI_PROGRAM_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/9/12
 * @Description:
 **/
@RestController
@RequestMapping(MINI_PROGRAM_PREFIX + "hospital")
public class MPHospitalController {

    @Resource
    MPHospitalService mpHospitalService;

    /**
     * 根据code查询医院信息
     * @param code String
     * @return ResultVo
     */
    @GetMapping("{code}")
    public ResultVo queryById(@PathVariable String code) {
        return mpHospitalService.getHospitalDetailByCode(code);
    }

}
