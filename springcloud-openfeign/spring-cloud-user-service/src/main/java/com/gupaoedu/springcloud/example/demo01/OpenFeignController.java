package com.gupaoedu.springcloud.example.demo01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class OpenFeignController {


    @Autowired
    OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping("/test")
    public String test(){
        return orderServiceFeignClient.getAllOrder();
    }
}
