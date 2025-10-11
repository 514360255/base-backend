package com.base.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 郭郭
 * @Create: 2025/9/18
 * @Description:
 **/
@Component
@ConfigurationProperties(prefix = "email")
@Data
public class SendEmailConfig {

    /**
     * 端口
     */
    private String port;

    /**
     * 邮箱
     */
    private String host;

    /**
     * 邮箱
     */
    private String formEmail;

    /**
     * 授权码
     */
    private String authCode;

}
