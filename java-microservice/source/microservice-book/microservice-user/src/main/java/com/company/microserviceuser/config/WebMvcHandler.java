package com.company.microserviceuser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置请求拦截器
 * @author xindaiq
 * @since 2021-01-15
 */
@Configuration
public class WebMvcHandler implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new TokenInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/**/swagger-ui.html");
        registration.excludePathPatterns("/**/swagger-resources/**");
        registration.excludePathPatterns("/**/v2/**");
        registration.excludePathPatterns("/**/webjars/**");
        registration.excludePathPatterns("/**/login");
        registration.excludePathPatterns("/**/error");
        registration.excludePathPatterns("/**/shiro/**");
        registration.excludePathPatterns("/**/test");

    }
}
