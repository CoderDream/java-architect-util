package com.demo.goods.restapi.controller;

import com.demo.goods.domain.entity.Picture;
import com.demo.goods.domain.service.PictureService;
import com.demo.goods.domain.util.CommonUtils;
import com.demo.goods.object.PictureQo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/picture")
public class PictureRestController {
    private static Logger logger = LoggerFactory.getLogger(PictureRestController.class);

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value="/{id}")
    public CompletableFuture<String> fnidById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Picture picture = pictureService.findOne(id);
            return new Gson().toJson(picture);
        });
    }


    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer index, Integer size, Long merchantid, String created) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                PictureQo pictureQo = new PictureQo();

                if(CommonUtils.isNotNull(index)){
                    pictureQo.setPage(index);
                }
                if(CommonUtils.isNotNull(size)){
                    pictureQo.setSize(size);
                }
                if(CommonUtils.isNotNull(merchantid)){
                    pictureQo.setMerchantid(merchantid);
                }
                if(CommonUtils.isNotNull(created)){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pictureQo.setCreated(sdf.parse(created));
                }

                Page<Picture> pictures = pictureService.findAll(pictureQo);

                Map<String, Object> page = new HashMap<>();
                page.put("content", pictures.getContent());
                page.put("totalPages", pictures.getTotalPages());
                page.put("totalelements", pictures.getTotalElements());

                return new Gson().toJson(page);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody PictureQo pictureQo) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Picture picture = new Picture();
            BeanUtils.copyProperties(pictureQo, picture);

            pictureService.save(picture);

            logger.info("新增->ID=" + picture.getId());
            return picture.getId().toString();
        });
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CompletableFuture<String> update(@RequestBody PictureQo pictureQo) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Picture picture = new Picture();
            BeanUtils.copyProperties(pictureQo, picture);


            pictureService.save(picture);

            logger.info("修改->ID=" + picture.getId());
            return picture.getId().toString();
        });
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            pictureService.delete(id);

            logger.info("删除->ID=" + id);
            return id.toString();
        });
    }

    @RequestMapping(value="/deleteByFileName/{fileName}",method = RequestMethod.DELETE)
    public CompletableFuture<String> deleteByFileName(@PathVariable String fileName) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            pictureService.deleteByFileName(fileName);

            logger.info("删除->ID=" + fileName);
            return "1";
        });
    }
}
