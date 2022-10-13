package com.gupaoedu.springcloud.example.demo01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }
   /* @Autowired
    LoadBalancerClient loadBalancerClient;*/

    @GetMapping("/user/{id}")
    public String findById(@PathVariable("id")int id){
        //TODO
        // 调用订单的服务获得订单信息
        // HttpClient  RestTemplate  OkHttp   JDK HttpUrlConnection
       /* ServiceInstance serviceInstance=loadBalancerClient.choose("spring-cloud-order-service");
        String url=String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()+"/orders");*/
        return restTemplate.getForObject("http://spring-cloud-order-service/orders",String.class);
    }

}
