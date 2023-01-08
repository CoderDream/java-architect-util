package com.demo.order.web.controller;

import com.demo.order.client.service.OrderFuture;
import com.demo.order.client.service.OrderRestService;
import com.demo.order.object.OrderQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private OrderFuture orderFuture;

    @Autowired
    private OrderRestService orderRestService;

    @RequestMapping(value="/list")
    public CompletableFuture<String> findAll() {
        OrderQo orderQo = new OrderQo();
        return orderFuture.findPage(orderQo).thenApply(page -> page);
    }

    @RequestMapping(value="/list1")
    public String findAll1() {
        OrderQo orderQo = new OrderQo();
        return orderRestService.findPage(orderQo);
    }
}
