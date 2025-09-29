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
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadConfig {

    private String url;

}
