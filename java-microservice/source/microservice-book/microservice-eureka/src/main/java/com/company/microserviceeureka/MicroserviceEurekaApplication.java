package com.company.microserviceeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注册中心Eureka
 * @author xindaqi
 * @since 2020-10-06
 */
@SpringBootApplication
@EnableEurekaServer
public class MicroserviceEurekaApplication {

	private static Logger logger = LoggerFactory.getLogger(MicroserviceEurekaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEurekaApplication.class, args);
		logger.info("注册中心启动");
	}

}
