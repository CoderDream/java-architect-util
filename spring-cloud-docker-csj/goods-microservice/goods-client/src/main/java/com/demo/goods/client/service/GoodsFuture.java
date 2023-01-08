package com.demo.goods.client.service;


import com.demo.goods.object.GoodsQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class GoodsFuture {
    @Autowired
    private GoodsRestService goodsRestService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return goodsRestService.findById(id);
        });
    }


    @AsyncTimed
    public CompletableFuture<String> findPage(GoodsQo goodsQo) {
        return CompletableFuture.supplyAsync(() -> {
            return goodsRestService.findPage(goodsQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(GoodsQo goodsQo) {
        return CompletableFuture.supplyAsync(() -> {
            return goodsRestService.create(goodsQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(GoodsQo goodsQo) {
        return CompletableFuture.supplyAsync(() -> {
            return goodsRestService.update(goodsQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return goodsRestService.delete(id);
        });
    }
}
