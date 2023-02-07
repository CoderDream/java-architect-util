package com.coderdream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.coderdream.easyexcelpractise.client")
public class EasyExcelPractiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyExcelPractiseApplication.class, args);
    }

}
