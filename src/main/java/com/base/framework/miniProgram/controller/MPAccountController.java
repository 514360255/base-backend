package com.base.framework.miniProgram.controller;

import com.base.framework.miniProgram.model.dto.account.AuthForm;
import com.base.framework.miniProgram.service.MPAccountService;
import com.base.framework.utils.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.MINI_PROGRAM_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
@RestController
@RequestMapping(MINI_PROGRAM_PREFIX + "account")
public class MPAccountController {

    @Resource
    MPAccountService mpAccountService;

    @PostMapping("login")
    public ResultVo login(@RequestBody AuthForm params) {
        return mpAccountService.login(params);
    }

}
