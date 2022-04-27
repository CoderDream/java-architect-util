package com.example.demo;

import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
public class DemoApplication {

    @Resource
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        UserService userService = null;
        try {
            userService = applicationContext.getBean(UserService.class);
        } catch (Exception e) {
            System.err.println("###1: " + e.getMessage());
        }

        OrderService orderService = null;
        try {
            orderService = applicationContext.getBean(OrderService.class);
        } catch (Exception e) {
            System.err.println("###2: " + e.getMessage());
        }

        return "userService=" + userService + "<br/>" +
                "orderService=" + orderService;
    }

}
