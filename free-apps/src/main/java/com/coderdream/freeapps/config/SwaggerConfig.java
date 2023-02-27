package com.coderdream.freeapps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket getUserDocket(){
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
//        parameters.add(parameterBuilder.name(Constant.TOKEN).description("token").modelRef(new ModelRef("string"))
//                .parameterType("header").required(false).build());
//        parameters.add(parameterBuilder.name(Constant.SECRET).description("secret").modelRef(new ModelRef("string"))
//                .parameterType("header").required(false).build());
        ApiInfo apiInfo=new ApiInfoBuilder()
                .title("XX系统API平台")//api标题
                .description("数据中心相关接口描述")//api描述
                .version("1.0.0")//版本号
                .contact(new Contact("XX科技",null,null))
                .build();
        return new Docket(DocumentationType.SWAGGER_2)//文档类型（swagger2）
                .apiInfo(apiInfo)//设置包含在json ResourceListing响应中的api元信息
                .select()//启动用于api选择的构建器
                .apis(RequestHandlerSelectors.basePackage("com.coderdream.freeapps.controller"))//扫描接口的包
                .paths(PathSelectors.any())//路径过滤器（扫描所有路径）
                .build().globalOperationParameters(parameters);
    }

}
