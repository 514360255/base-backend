package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.user.UserQueryRequest;
import com.base.framework.common.BaseResponse;
import com.base.framework.common.ErrorCode;
import com.base.framework.common.ResultUtils;
import com.base.framework.exception.BusinessException;
import com.base.framework.admin.model.dto.user.UserLoginRequest;
import com.base.framework.admin.model.vo.LoginUserVO;
import com.base.framework.admin.model.vo.AccountVO;
import com.base.framework.admin.service.AccountService;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * 用户接口
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
        LoginUserVO user = accountService.getLoginUser(request);
        return ResultUtils.success(user);
    }

    @GetMapping("/userList")
    public BaseResponse<PageInfo> queryUserList(@Param("params") UserQueryRequest params) {
        return ResultUtils.success(accountService.queryUserList(params));
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = accountService.userLogout(request);
        return ResultUtils.success(result);
    }

}
