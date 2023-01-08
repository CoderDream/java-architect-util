package com.demo.order.restapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@ComponentScan(basePackages = "com.demo")
public class OrderRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderRestApiApplication.class, args);
    }
}
