package com.example.demo.config;

import com.example.demo.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "hxstrive.service", name = "user.enable",
        havingValue = "1")
public class UserConfig {

    @Bean
    public UserService userService() {
        System.out.println("UserService -> userService()");
        return new UserService();
    }

}
