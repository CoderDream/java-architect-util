package com.gupaoedu.example.clients;

import com.gupaoedu.example.OrderService;
import org.springframework.cloud.openfeign.FeignClient;

/**
feign定义，直接继承OrderService，加上 @FeignClient("order-service")注解即可
 **/

@FeignClient("order-service")
public interface OrderServiceFeignClient extends OrderService{

}
