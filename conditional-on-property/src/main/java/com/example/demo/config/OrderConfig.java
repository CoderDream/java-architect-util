package com.example.demo.config;

import com.example.demo.service.OrderService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "hxstrive.service", name = "order.enable",
        havingValue = "1")
public class OrderConfig {

    @Bean
    public OrderService orderService() {
        System.out.println("OrderConfig -> orderService()");
        return new OrderService();
    }

}
