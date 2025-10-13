package com.base.framework.Interceptor;

import com.base.framework.utils.JwtTokenUtils;
import com.base.framework.utils.SecurityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.base.framework.constant.JwtConstant.HEADER_TOKEN;

/**
 * @author 郭郭
 * @date 2023/11/6 10:13
 * @DESCRIPTION
 */
@Component
public class MiniProgramInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader(HEADER_TOKEN);

        if(token == null) {
            responseHandle(response, 401, "请登录");
            return false;
        }

        Claims claims = JwtTokenUtils.getTokenClaim(token);
        if (claims == null) {
            responseHandle(response, 401, "身份过期，请重新登录");
            SecurityUtils.removeCurrentUser();
            return false;
        }

        // 校验身份是否过期
        if(JwtTokenUtils.isExpiration(token)) {
            responseHandle(response, 401, "身份过期，请重新登录");
            SecurityUtils.removeCurrentUser();
            return false;
        }

        // 判断内存中用户信息是否存在
        Long userId = JwtTokenUtils.getUserId(token);
        if(userId == null) {
            responseHandle(response, 401, "请登录");
            return false;
        }

        return true;
    }

    public void responseHandle(HttpServletResponse response, Integer code, String message) throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("success", true);
        result.put("message", message);
        String str = new ObjectMapper().writeValueAsString(result);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(code);
        response.getWriter().println(str);
    }

}
