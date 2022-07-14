package com.coderdream.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }


    @Bean
    public Docket docket2(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }


    @Bean
    public Docket docket3(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    @Bean
    public Docket docket(Environment environment) {
        // 设置要显示
        Profiles profiles = Profiles.of("dev", "test");
        // 获取项目的环境
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("hello")
                .enable(flag)
                .select()
                // RequestHandlerSelectors，配置要扫描接口的方式
                // basePackage：指定要扫描的包
                // any()：扫描全部
                // withClassAnnotation：扫描类上的注解
                // withMethodAnnotation：扫描方法上的注解
                // .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.coderdream.controller"))
                //    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //   .paths(PathSelectors.ant("/com/coderdream/controller/**"))
                .build();
    }

    /**
     * 配置Swagger的信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("CoderDream", "https://coderdream.github.io", "coderdream@gmail.com");
        return new ApiInfo(
                "CoderDream's Studio",
                "码农的梦想家园",
                "v1.0",
                "https://coderdream.github.io",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>()
        );
    }
}
