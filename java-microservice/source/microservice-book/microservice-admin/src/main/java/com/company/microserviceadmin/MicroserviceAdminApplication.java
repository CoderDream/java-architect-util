package com.company.microserviceadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Admin模块启动
 * @author xindaqi
 * @since 2021-01-04
 */

@SpringBootApplication
@EnableAdminServer
public class MicroserviceAdminApplication {

	private static Logger logger = LoggerFactory.getLogger(MicroserviceAdminApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAdminApplication.class, args);
		logger.info("启动--微服务Admin模块");
	}

}
