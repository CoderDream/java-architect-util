package com.coderdream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CoderDream
 */
@MapperScan("com.coderdream.demos.web.mapper")
@SpringBootApplication
public class WebmagicSpiderJobsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebmagicSpiderJobsApplication.class, args);
    }

}
