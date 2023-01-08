package com.demo.order.wap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
@EnableFeignClients(basePackages = "com.demo")
@ComponentScan(basePackages = "com.demo")
public class OrderWapApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderWapApplication.class, args);
    }

}
