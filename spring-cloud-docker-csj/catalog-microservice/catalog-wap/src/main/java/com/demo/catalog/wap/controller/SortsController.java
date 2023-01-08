package com.demo.catalog.wap.controller;

import com.demo.catalog.client.service.SortsFuture;
import com.demo.catalog.client.util.TreeMapConvert;
import com.demo.catalog.object.SortsQo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/sorts")
public class SortsController {
    private static Logger logger = LoggerFactory.getLogger(SortsController.class);

    @Autowired
    private SortsFuture sortsFuture;

    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping(value="/index")
    public CompletableFuture<ModelAndView> findAll() {
        return sortsFuture.findList().thenApply(json -> {
            Gson gson = TreeMapConvert.getGson();
            List<SortsQo> sortses = gson.fromJson(json, new TypeToken<List<SortsQo>>(){}.getType());
            logger.info("sorts list json={}", json);
            return new ModelAndView("sorts/index", "sortses", sortses);
        });
    }

    @RequestMapping(value="/service/{name}")
    public String getService(@PathVariable String name) {
        List<ServiceInstance> list = discoveryClient.getInstances(name);
        String serviceUri = "./";
        if(list != null && list.size() > 0){
            if(list.size() > 1) {
                Random random = new Random();
                ServiceInstance service = list.get(random.nextInt(list.size() - 1));
                serviceUri = service.getUri().toString();
            }else {
                ServiceInstance service = list.get(0);
                serviceUri = service.getUri().toString();
            }
        }
        logger.info("serviceUri={}", serviceUri);
        return serviceUri;
    }
}
