package com.demo.merchant.client.service;

import com.demo.merchant.object.RoleQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class RoleFuture {
    @Autowired
    private RoleRestService roleRestService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return roleRestService.findById(id);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            return roleRestService.findByName(name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findList() {
        return CompletableFuture.supplyAsync(() -> {
            return roleRestService.findList();
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findPage(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            return roleRestService.findPage(index, size, name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(RoleQo roleQo) {
        return CompletableFuture.supplyAsync(() -> {
            return roleRestService.create(roleQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(RoleQo roleQo) {
        return CompletableFuture.supplyAsync(() -> {
            return roleRestService.update(roleQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return roleRestService.delete(id);
        });
    }
}
