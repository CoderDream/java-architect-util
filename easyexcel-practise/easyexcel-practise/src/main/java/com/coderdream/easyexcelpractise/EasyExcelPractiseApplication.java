package com.coderdream.easyexcelpractise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.coderdream.easyexcelpractise.client")
public class EasyExcelPractiseApplication {

    public static void main(String[] args) {

//        org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata c;


        SpringApplication.run(EasyExcelPractiseApplication.class, args);
    }

}
