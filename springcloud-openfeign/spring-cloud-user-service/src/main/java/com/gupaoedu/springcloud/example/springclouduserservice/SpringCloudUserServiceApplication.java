package com.gupaoedu.springcloud.example.springclouduserservice;

import com.gupaoedu.springcloud.example.demo02.EnableGpRegistrara;
import com.gupaoedu.springcloud.example.demo02.EnableMyRegistrar;
import com.gupaoedu.springcloud.example.demo02.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@EnableMyRegistrar
//@EnableGpRegistrara
// 第三方包里面声明的feign接口，不在SpringBootApplication同级目录下，需要手动指定@FeignClient注解扫描包路径
@EnableFeignClients(basePackages = "com.gupaoedu.example.clients")
@SpringBootApplication
public class SpringCloudUserServiceApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext contxt=SpringApplication.run(SpringCloudUserServiceApplication.class, args);
        System.out.println(contxt.getBean(HelloService.class));
        System.out.println(contxt.getBean("myhelloService"));
    }

}
