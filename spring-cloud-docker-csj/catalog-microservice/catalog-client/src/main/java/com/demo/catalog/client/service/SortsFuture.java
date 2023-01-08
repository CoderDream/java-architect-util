package com.demo.catalog.client.service;


import com.demo.catalog.object.SortsQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class SortsFuture {
    @Autowired
    private SortsRestService ordersService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.findById(id);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findList() {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.findList();
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findPage(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.findPage(index, size, name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(SortsQo sortsQo) {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.create(sortsQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(SortsQo sortsQo) {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.update(sortsQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return ordersService.delete(id);
        });
    }
}
