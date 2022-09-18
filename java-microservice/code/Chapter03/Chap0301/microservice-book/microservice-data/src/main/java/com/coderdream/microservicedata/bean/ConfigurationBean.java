package com.coderdream.microservicedata.bean;

import com.coderdream.microservicedata.constant.StringConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration创建Bean
 * @author xindaqi
 * @since 2021-01-23
 */
@Configuration
public class ConfigurationBean {

    @Bean(name = "myConfigurationBean")
    public String myConfigurationBean() {
        return StringConstant.CONFIGURATION_BEAN;
    }
}
