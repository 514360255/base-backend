package com.base.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/10/11
 * @Description:
 **/

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // 获取默认的消息转换器
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();

        // 找到 MappingJackson2HttpMessageConverter 并修改其支持的 media type
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter =
                        (MappingJackson2HttpMessageConverter) converter;

                // 添加对 text/plain 的支持
                List<MediaType> supportedMediaTypes = new ArrayList<>(
                        jsonConverter.getSupportedMediaTypes());
                supportedMediaTypes.add(MediaType.TEXT_PLAIN);
                jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
            }
        }

        return restTemplate;
    }
}