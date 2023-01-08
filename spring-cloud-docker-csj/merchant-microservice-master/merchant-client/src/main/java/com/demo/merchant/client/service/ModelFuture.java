package com.demo.merchant.client.service;

import com.demo.merchant.object.ModelQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ModelFuture {
    @Autowired
    private ModelRestService modelRestService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return modelRestService.findById(id);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            return modelRestService.findByName(name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findList() {
        return CompletableFuture.supplyAsync(() -> {
            return modelRestService.findList();
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findPage(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            return modelRestService.findPage(index, size, name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(ModelQo modelQo) {
        return CompletableFuture.supplyAsync(() -> {
            return modelRestService.create(modelQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(ModelQo modelQo) {
        return CompletableFuture.supplyAsync(() -> {
            return modelRestService.update(modelQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return modelRestService.delete(id);
        });
    }
}
