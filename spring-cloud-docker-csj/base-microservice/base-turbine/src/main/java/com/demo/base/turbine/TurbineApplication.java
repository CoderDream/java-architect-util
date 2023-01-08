package com.demo.base.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
@EnableHystrixDashboard
@Controller
public class TurbineApplication {
    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix";
    }

    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}
