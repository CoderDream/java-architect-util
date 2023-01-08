package com.demo.goods.client.service;

import com.demo.goods.object.PictureQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class PictureFuture {
    @Autowired
    private PictureRestService pictureRestService;

    @AsyncTimed
    public CompletableFuture<String> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return pictureRestService.findById(id);
        });
    }


    @AsyncTimed
    public CompletableFuture<String> findPage(PictureQo pictureQo) {
        return CompletableFuture.supplyAsync(() -> {
            return pictureRestService.findPage(pictureQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> create(PictureQo pictureQo) {
        return CompletableFuture.supplyAsync(() -> {
            return pictureRestService.create(pictureQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> update(PictureQo pictureQo) {
        return CompletableFuture.supplyAsync(() -> {
            return pictureRestService.update(pictureQo);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return pictureRestService.delete(id);
        });
    }

    @AsyncTimed
    public CompletableFuture<String> deleteByFileName(String fileName) {
        return CompletableFuture.supplyAsync(() -> {
            return pictureRestService.deleteByFileName(fileName);
        });
    }
}
