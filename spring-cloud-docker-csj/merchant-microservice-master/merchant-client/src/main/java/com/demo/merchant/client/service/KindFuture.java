package com.demo.merchant.client.service;

import com.demo.merchant.object.KindQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KindFuture {
    @Autowired
    private KindRestService kindRestService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return kindRestService.findById(id);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            return kindRestService.findByName(name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findList() {
        return CompletableFuture.supplyAsync(() -> {
            return kindRestService.findList();
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findPage(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            return kindRestService.findPage(index, size, name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(KindQo kindQo) {
        return CompletableFuture.supplyAsync(() -> {
            return kindRestService.create(kindQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(KindQo kindQo) {
        return CompletableFuture.supplyAsync(() -> {
            return kindRestService.update(kindQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return kindRestService.delete(id);
        });
    }
}
