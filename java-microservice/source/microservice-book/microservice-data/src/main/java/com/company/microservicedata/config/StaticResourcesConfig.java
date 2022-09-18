package com.company.microservicedata.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源读取配置
 * @author xindaqi
 * @since 2020-12-19
 */

@Configuration
public class StaticResourcesConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/v1/data/download/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/template/");
    }
}
