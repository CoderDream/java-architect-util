package com.gupaoedu.example;

import com.gupaoedu.example.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
接口描述
 **/
public interface OrderService {

    @GetMapping("/orders")
    String orders();

    @PostMapping("/order")
    int insert(OrderDto dto);
}
