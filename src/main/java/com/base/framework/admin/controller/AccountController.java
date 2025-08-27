package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.user.UserQueryRequest;
import com.base.framework.common.BaseResponse;
import com.base.framework.common.ErrorCode;
import com.base.framework.common.ResultUtils;
import com.base.framework.exception.BusinessException;
import com.base.framework.admin.model.dto.user.UserLoginRequest;
import com.base.framework.admin.model.vo.LoginUserVO;
import com.base.framework.admin.service.AccountService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import static com.base.framework.constant.JwtConstant.HEADER_TOKEN;
import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * 用户接口
 * @author guojiuling
 */
@RestController
@RequestMapping(ADMIN_PREFIX + "/account")
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 用户登录
     *
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getAccount();
        String userPassword = userLoginRequest.getPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LoginUserVO loginUserVO = accountService.userLogin(userAccount, userPassword);
        return ResultUtils.success(loginUserVO);
    }

    @GetMapping("/userInfo")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        String token = request.getHeader(HEADER_TOKEN);
        LoginUserVO user = accountService.getLoginUser(token);
        return ResultUtils.success(user);
    }

    @GetMapping("/userList")
    public BaseResponse<PageInfo> queryUserList(@Param("params") UserQueryRequest params) {
        return ResultUtils.success(accountService.queryUserList(params));
    }

    /**
     * 用户注销
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        String token = request.getHeader(HEADER_TOKEN);
        if (token == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR, ErrorCode.NOT_LOGIN_ERROR.getMessage());
        }
        boolean result = accountService.userLogout();
        return ResultUtils.success(result);
    }

}
