package com.demo.merchant.client.service;

import com.demo.merchant.object.MerchantQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class MerchantFuture {
    @Autowired
    private MerchantRestService merchantRestService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return merchantRestService.findById(id);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            return merchantRestService.findByName(name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findList() {
        return CompletableFuture.supplyAsync(() -> {
            return merchantRestService.findList();
        });
    }

    @AsyncTimed
    public CompletableFuture<String> findPage(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            return merchantRestService.findPage(index, size, name);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(MerchantQo merchantQo) {
        return CompletableFuture.supplyAsync(() -> {
            return merchantRestService.create(merchantQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(MerchantQo merchantQo) {
        return CompletableFuture.supplyAsync(() -> {
            return merchantRestService.update(merchantQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return merchantRestService.delete(id);
        });
    }
}
