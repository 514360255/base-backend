package com.base.framework.aop;

import com.base.framework.admin.model.vo.LoginUserVO;
import com.base.framework.annotation.AuthCheck;
import com.base.framework.common.ErrorCode;
import com.base.framework.exception.BusinessException;
import com.base.framework.admin.model.enums.UserRoleEnum;
import com.base.framework.admin.service.AccountService;
import com.base.framework.utils.JwtTokenUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.base.framework.constant.JwtConstant.HEADER_TOKEN;

/**
 * 权限校验 AOP
 * @author guojiuling
 */
@Component
@Aspect
public class AuthInterceptor {

    @Resource
    private AccountService userService;

    /**
     * 执行拦截
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        String token = request.getHeader(HEADER_TOKEN);
        LoginUserVO loginUser = userService.getLoginUser(token);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
//        if(loginUser != null) {
//            UserContextUtils.setUser(loginUser);
//        }
        // 不需要权限，放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        // 必须有该权限才通过
        assert loginUser != null;
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(String.valueOf(loginUser.getRoleCode()));
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 如果被封号，直接拒绝
        if (UserRoleEnum.BAN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 必须有管理员权限
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum)) {
            // 用户没有管理员权限，拒绝
            if (!UserRoleEnum.ADMIN.equals(userRoleEnum)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}

