package com.coderdream.middleware.server;

import com.github.xiaoymin.knife4j.spring.configuration.insight.Knife4jInsightAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 应用启动类-入口
 * @Author:
 **/
@SpringBootApplication
public class MainApplication {//} extends SpringBootServletInitializer {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return super.configure(builder);
//    }

    public static void main(String[] args) {
        Knife4jInsightAutoConfiguration knife4jInsightAutoConfiguration = null;
        SpringApplication.run(MainApplication.class,args);
    }

}
