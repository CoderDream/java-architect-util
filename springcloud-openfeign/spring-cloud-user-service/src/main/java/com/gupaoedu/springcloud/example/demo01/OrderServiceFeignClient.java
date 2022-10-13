package com.gupaoedu.springcloud.example.demo01;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@FeignClient(value = "spring-cloud-order-service")
public interface OrderServiceFeignClient {

    @GetMapping("/orders")
    String getAllOrder();
}
