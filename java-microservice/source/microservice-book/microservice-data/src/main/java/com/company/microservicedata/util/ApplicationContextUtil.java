package com.company.microservicedata.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 上下文获取Bean
 * @author xindaqi
 * @since 2020-12-28
 */
@Component
public class ApplicationContextUtil {

    @Autowired
    private ApplicationContext applicationContext;

    public String[] getAllBeans() {
        return applicationContext.getBeanDefinitionNames();
    }

    public String[] getBeansByType(Class<?> clzz) {
        return applicationContext.getBeanNamesForType(clzz);
    }

    public Object getObjectByType(Class<?> clzz) {
        return applicationContext.getBean(clzz);
    }

}
