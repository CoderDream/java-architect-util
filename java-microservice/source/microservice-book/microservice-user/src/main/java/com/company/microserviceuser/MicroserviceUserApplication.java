package com.company.microserviceuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.cache.annotation.EnableCaching;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.company.microserviceuser.config.ThreadPoolConfig;

/**
 * 用户服务启动
 * 
 * @author xindaqi
 * @since 2020-10-26
 */

@SpringBootApplication
// @MapperScan("com.company.microserviceuser.dao")
@EnableConfigurationProperties(ThreadPoolConfig.class)
public class MicroserviceUserApplication {
	private static Logger logger = LoggerFactory.getLogger(MicroserviceUserApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUserApplication.class, args);
		logger.info("启动--微服务用户模块");
	}

}
