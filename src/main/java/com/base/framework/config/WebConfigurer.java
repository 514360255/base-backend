package com.base.framework.config;

import com.base.framework.Interceptor.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 郭郭
 * @date 2023/11/6 10:13
 * @DESCRIPTION
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Resource
    private AdminInterceptor adminInterceptor;

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegister) {

        // 后端登录
        interceptorRegister
                .addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/account/login");
    }


}
