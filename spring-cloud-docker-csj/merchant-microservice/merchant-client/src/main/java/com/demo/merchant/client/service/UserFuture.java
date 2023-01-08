package com.demo.merchant.client.service;

import com.demo.merchant.object.UserQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class UserFuture {
    @Autowired
    private UserRestService userRestService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.findById(id);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.findByName(name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findList() {
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.findList();
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findPage(UserQo userQo) {
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.findPage(userQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(UserQo userQo) {
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.create(userQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(UserQo userQo) {
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.update(userQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.delete(id);
        });
    }
}
