package com.coderdream.microservicedata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource(locations={"classpath:xml/applicationContext.xml"})
public class MicroserviceDataApplication {
    private static Logger logger = LoggerFactory.getLogger(MicroserviceDataApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceDataApplication.class, args);
        logger.info("启动--微服务数据模块");
    }
}
