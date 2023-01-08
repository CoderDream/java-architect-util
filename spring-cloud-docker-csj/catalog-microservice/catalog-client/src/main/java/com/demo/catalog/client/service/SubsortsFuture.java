package com.demo.catalog.client.service;


import com.demo.catalog.object.SubsortsQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class SubsortsFuture {
    @Autowired
    private SubsortsRestService subsortsRestService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return subsortsRestService.findById(id);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            return subsortsRestService.findByName(name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            return subsortsRestService.findAll();
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findPage(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            return subsortsRestService.findAll(index, size, name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(SubsortsQo subsortsQo) {
        return CompletableFuture.supplyAsync(() -> {
            return subsortsRestService.create(subsortsQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(SubsortsQo subsortsQo) {
        return CompletableFuture.supplyAsync(() -> {
            return subsortsRestService.update(subsortsQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return subsortsRestService.delete(id);
        });
    }
}
