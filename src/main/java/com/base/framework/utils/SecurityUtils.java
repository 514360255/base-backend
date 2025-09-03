package com.base.framework.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.base.framework.admin.model.vo.CustomUserDetailsVO;

import java.util.Objects;

/**
 * @Author: 郭郭
 * @Create: 2025/8/19
 * @Description:
 **/
public class SecurityUtils {

    public static void removeCurrentUser() {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(null, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static void setCurrentUser(CustomUserDetailsVO userInfo) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userInfo, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static CustomUserDetailsVO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetailsVO) {
            return (CustomUserDetailsVO) principal;
        }
        return null;
    }

    public static String getCurrentUsername() {
        CustomUserDetailsVO user = getCurrentUser();
        return user != null ? user.getName() : null;
    }

    public static Boolean isSuperAdmin () {
        CustomUserDetailsVO user = getCurrentUser();
        if(user == null) {
            return false;
        }
        return Objects.equals(user.getRoleCode(), "SUPER_ADMIN");
    }

    public static String getRoleCode() {
        CustomUserDetailsVO user = getCurrentUser();
        return user != null ? user.getRoleCode() : null;
    }

    public static Long getCurrentUserId() {
        CustomUserDetailsVO user = getCurrentUser();
        return user != null ? user.getId() : null;
    }
}
