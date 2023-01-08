package com.demo.goods.wap.controller;

import com.demo.goods.client.service.GoodsFuture;
import com.demo.goods.client.util.TreeMapConvert;
import com.demo.goods.object.GoodsQo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsFuture goodsFuture;
    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping(value="/index")
    public ModelAndView index(ModelMap model, HttpServletRequest request) throws Exception{
        String sortsid = request.getParameter("sortsid");
        return new ModelAndView("goods/index", "sortsid", sortsid);
    }


    @RequestMapping(value="/{id}")
    public CompletableFuture<ModelAndView> findById(@PathVariable Long id) {
        return goodsFuture.findById(id).thenApply(json -> {
            GoodsQo goodsQo = new Gson().fromJson(json, GoodsQo.class);
            return new ModelAndView("goods/show", "goods", goodsQo);
        });
    }


    @RequestMapping(value = "/list")
    public CompletableFuture<Page<Map<String, Object>>> findAll(GoodsQo goodsQo) {
        return goodsFuture.findPage(goodsQo).thenApply(json -> {
            logger.info("goods list = {}", json);
            Gson gson = TreeMapConvert.getGson();
            TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

            Pageable pageable = new PageRequest(goodsQo.getPage(), goodsQo.getSize(), null);
            List<GoodsQo> list = new ArrayList<>();

            if(page != null && page.get("content") != null)
                list = gson.fromJson(page.get("content").toString(), new TypeToken<List<GoodsQo>>(){}.getType());
            String count = page.get("totalelements").toString();

            return new PageImpl(list, pageable, new Long(count));
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
