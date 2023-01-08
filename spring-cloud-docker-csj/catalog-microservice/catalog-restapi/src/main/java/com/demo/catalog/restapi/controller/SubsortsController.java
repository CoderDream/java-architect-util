package com.demo.catalog.restapi.controller;


import com.demo.catalog.domain.entity.Sorts;
import com.demo.catalog.domain.entity.Subsorts;
import com.demo.catalog.domain.service.SortsService;
import com.demo.catalog.domain.service.SubsortsService;
import com.demo.catalog.domain.util.CommonUtils;
import com.demo.catalog.object.SubsortsQo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/subsorts")
public class SubsortsController {
    private static Logger logger = LoggerFactory.getLogger(SubsortsController.class);

    @Autowired
    private SubsortsService subsortsService;
    @Autowired
    private SortsService sortsService;

    @RequestMapping(value="/{id}")
    public CompletableFuture<String> fnidById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Subsorts subsorts = subsortsService.findOne(id);
            return new Gson().toJson(subsorts);
        });
    }

    @RequestMapping(value="/names/{name}")
    public CompletableFuture<String> findByName(@PathVariable String name) {
        return CompletableFuture.supplyAsync(() -> {
            Subsorts subsorts = subsortsService.findByName(name);
            return new Gson().toJson(subsorts);
        });
    }

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            try{
                SubsortsQo subsortsQo = new SubsortsQo();

                if(CommonUtils.isNotNull(index)){
                    subsortsQo.setPage(index);
                }
                if(CommonUtils.isNotNull(size)){
                    subsortsQo.setSize(size);
                }
                if(CommonUtils.isNotNull(name)){
                    subsortsQo.setName(name);
                }

                Page<Subsorts> subsortses = subsortsService.findAll(subsortsQo);

                Map<String, Object> page = new HashMap<>();
                page.put("content", subsortses.getContent());
                page.put("totalPages", subsortses.getTotalPages());
                page.put("totalelements", subsortses.getTotalElements());

                return new Gson().toJson(page);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(value = "/findAll")
    public CompletableFuture<String> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Iterable<Subsorts> subses = subsortsService.findAll();
                return new Gson().toJson(subses);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody SubsortsQo subsortsQo, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Subsorts subsorts = new Subsorts();
            BeanUtils.copyProperties(subsortsQo, subsorts);
            subsorts.setCreated(new Date());

            subsortsService.save(subsorts);
            logger.info("新增->ID=" + subsorts.getId());
            return subsorts.getId().toString();
        });
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CompletableFuture<String> update(@RequestBody SubsortsQo subsortsQo) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Subsorts subsorts = new Subsorts();
            BeanUtils.copyProperties(subsortsQo, subsorts);

            subsortsService.save(subsorts);
            logger.info("修改->ID=" + subsorts.getId());
            return subsorts.getId().toString();
        });
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            //如果存在父类则先删除关系
            Sorts sorts = sortsService.findBySubsortsId(id);
            if(sorts != null){
                for(Subsorts subsorts : sorts.getSubsortses()){
                    if(subsorts.getId().compareTo(id) == 0){
                        sorts.getSubsortses().remove(subsorts);
                        sortsService.save(sorts);
                        break;
                    }
                }
            }
            subsortsService.delete(id);
            logger.info("删除->ID=" + id);
            return id.toString();
        });
    }

}
