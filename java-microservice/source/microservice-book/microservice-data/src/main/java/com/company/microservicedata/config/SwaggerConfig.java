package com.company.microservicedata.config;

import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.core.env.Profiles;
import org.springframework.core.env.Environment;

/**
 * Swagger Configure.
 * @author xindaqi
 * @since 2020-12-02
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(Environment environment){
        Profiles profiles = Profiles.of("dev", "test", "mac");
        boolean b = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(b)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.company.microservicedata.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("数据模块接口文档")
                .contact(new Contact("xindaqi", "xdq101@qq.com", "xdq101@qq.com"))
                .version("1.0")
                .description("个人信息")
                .termsOfServiceUrl("http://localhost:8888/api/v1")
                .build();
    }
}