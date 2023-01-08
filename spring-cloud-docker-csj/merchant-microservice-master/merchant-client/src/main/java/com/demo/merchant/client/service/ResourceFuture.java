package com.demo.merchant.client.service;

import com.demo.merchant.object.ResourceQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ResourceFuture {
    @Autowired
    private ResourceRestService resourceRestService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return resourceRestService.findById(id);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            return resourceRestService.findByName(name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findList() {
        return CompletableFuture.supplyAsync(() -> {
            return resourceRestService.findList();
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findPage(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            return resourceRestService.findPage(index, size, name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(ResourceQo resourceQo) {
        return CompletableFuture.supplyAsync(() -> {
            return resourceRestService.create(resourceQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(ResourceQo resourceQo) {
        return CompletableFuture.supplyAsync(() -> {
            return resourceRestService.update(resourceQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return resourceRestService.delete(id);
        });
    }
}
