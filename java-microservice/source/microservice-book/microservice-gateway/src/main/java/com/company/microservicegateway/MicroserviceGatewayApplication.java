package com.company.microservicegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网关服务
 * @author xindaqi
 * @since 2020-12-14
 */

@SpringBootApplication
public class MicroserviceGatewayApplication {

	private static Logger logger = LoggerFactory.getLogger(MicroserviceGatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayApplication.class, args);
		logger.info("启动网关服务");
	}

}
