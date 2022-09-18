package com.company.microserviceuser.config;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.core.env.Profiles;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger Configure.
 * @author xindaqi
 * @since 2020-10-26
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(Environment environment){
        ParameterBuilder token = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<>();
        token.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameterList.add(token.build());
        Profiles profiles = Profiles.of("dev", "test", "mac");
        boolean b = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(b)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.company.microserviceuser.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameterList);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("用户模块接口文档")
                .contact(new Contact("xindaqi", "xdq101@qq.com", "xdq101@qq.com"))
                .version("1.0")
                .description("个人信息")
                .termsOfServiceUrl("http://localhost:8888/api/v1")
                .build();
    }
}