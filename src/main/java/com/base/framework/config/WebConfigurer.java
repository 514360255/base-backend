package com.base.framework.config;

import com.base.framework.Interceptor.AdminInterceptor;
import com.base.framework.Interceptor.MiniProgramInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;
import static com.base.framework.constant.RouteConstant.MINI_PROGRAM_PREFIX;

/**
 * @author 郭郭
 * @date 2023/11/6 10:13
 * @DESCRIPTION
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Resource
    private AdminInterceptor adminInterceptor;

    @Resource
    private MiniProgramInterceptor miniProgramInterceptor;

    @Autowired
    private UploadConfig uploadConfig;

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegister) {

        // 后台系统
        interceptorRegister
                .addInterceptor(adminInterceptor)
                .addPathPatterns("/" + ADMIN_PREFIX + "**")
                .excludePathPatterns("/" + ADMIN_PREFIX + "account/login");

        // 小程序
        interceptorRegister
                .addInterceptor(miniProgramInterceptor)
                .addPathPatterns("/" + MINI_PROGRAM_PREFIX +"/**")
                .excludePathPatterns(
                        "/" + MINI_PROGRAM_PREFIX + "hospital/**",
                        "/" + MINI_PROGRAM_PREFIX + "appointment",
                        "/" + MINI_PROGRAM_PREFIX + "appointment/expert/**",
                        "/" + MINI_PROGRAM_PREFIX + "account/phone/auth"
                );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 覆盖所有请求
        // 加日志确认是否执行
        System.out.println(">>> CORS Config Applied");
        registry.addMapping("/**")
                // 允许发送 Cookie
                .allowCredentials(true)
                // 放行哪些域名（必须用 patterns，否则 * 会和 allowCredentials 冲突）
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry resourceHandlerRegistry) {
        // 映射 /uploads/** 到本地文件夹// 配置静态资源映射
        String userHome = System.getProperty("user.home");
        String url = uploadConfig.getUrl();
        if(url != null) {
            String uploadPath = userHome + "/" + url + "/";
            resourceHandlerRegistry.addResourceHandler("/uploads/**")
                    .addResourceLocations("file:" + uploadPath);
        }
    }


}
