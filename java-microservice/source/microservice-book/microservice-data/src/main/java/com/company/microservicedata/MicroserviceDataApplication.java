package com.company.microservicedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportResource;

/**
 * 数据模块启动
 * @author xindaqi
 * @since 2020-12-13
 */

@SpringBootApplication
@EnableFeignClients
@ImportResource(locations={"classpath:xml/applicationContext.xml"})
public class MicroserviceDataApplication {

	private static Logger logger = LoggerFactory.getLogger(MicroserviceDataApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceDataApplication.class, args);
		logger.info("启动--微服务数据模块");
	}

}
