package com.demo.catalog.restapi.controller;


import com.demo.catalog.domain.entity.Sorts;
import com.demo.catalog.domain.entity.Subsorts;
import com.demo.catalog.domain.service.SortsService;
import com.demo.catalog.domain.util.CommonUtils;
import com.demo.catalog.object.SortsQo;
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
@RequestMapping("/sorts")
public class SortsController {
    private static Logger logger = LoggerFactory.getLogger(SortsController.class);

    @Autowired
    private SortsService sortsService;

    @RequestMapping(value="/{id}")
    public CompletableFuture<String> fnidById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Sorts sorts = sortsService.findOne(id);
            return new Gson().toJson(sorts);
        });
    }

    @RequestMapping(value="/names/{name}")
    public CompletableFuture<String> findByName(@PathVariable String name) {
        return CompletableFuture.supplyAsync(() -> {
            Sorts sorts = sortsService.findByName(name);
            return new Gson().toJson(sorts);
        });
    }

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SortsQo sortsQo = new SortsQo();
                if(CommonUtils.isNotNull(index)){
                    sortsQo.setPage(index);
                }
                if(CommonUtils.isNotNull(size)){
                    sortsQo.setSize(size);
                }
                if(CommonUtils.isNotNull(name)){
                    sortsQo.setName(name);
                }

                Page<Sorts> orderses = sortsService.findAll(sortsQo);

                Map<String, Object> page = new HashMap<>();
                page.put("content", orderses.getContent());
                page.put("totalPages", orderses.getTotalPages());
                page.put("totalelements", orderses.getTotalElements());

                return new Gson().toJson(page);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(value = "/findAll")
    public CompletableFuture<String> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Iterable<Sorts> sortses = sortsService.findAll();
                return new Gson().toJson(sortses);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody SortsQo sortsQo, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Sorts sorts = new Sorts();
            BeanUtils.copyProperties(sortsQo, sorts);
            sorts.setCreated(new Date());

            for(SubsortsQo subsortsQo : sortsQo.getSubsortses()){
                Subsorts subsorts = new Subsorts();
                BeanUtils.copyProperties(subsortsQo, subsorts);
                sorts.addSubsorts(subsorts);
            }

            sortsService.save(sorts);
            logger.info("新增->ID=" + sorts.getId());
            return sorts.getId().toString();
        });
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CompletableFuture<String> update(@RequestBody SortsQo sortsQo) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Sorts sorts = new Sorts();
            BeanUtils.copyProperties(sortsQo, sorts);

            for(SubsortsQo subsortsQo : sortsQo.getSubsortses()){
                Subsorts subsorts = new Subsorts();
                BeanUtils.copyProperties(subsortsQo, subsorts);
                sorts.addSubsorts(subsorts);
            }

            sortsService.save(sorts);
            logger.info("修改->ID=" + sorts.getId());
            return sorts.getId().toString();
        });
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            sortsService.delete(id);
            logger.info("删除->ID=" + id);
            return id.toString();
        });
    }

}
