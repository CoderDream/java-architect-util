package com.demo.order.client.service;


import com.demo.order.object.OrderQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class OrderFuture {
    @Autowired
    private OrderRestService ordersService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.findById(id);
        });
    }


    @AsyncTimed
    public CompletableFuture<String> findPage(OrderQo orderQo) {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.findPage(orderQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(OrderQo orderQo) {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.create(orderQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(OrderQo orderQo) {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.update(orderQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.delete(id);
        });
    }
}
