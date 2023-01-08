package com.demo.order.wap.controller;


import com.demo.goods.client.service.GoodsFuture;
import com.demo.goods.client.service.GoodsRestService;
import com.demo.goods.object.GoodsQo;
import com.demo.order.client.service.OrderFuture;
import com.demo.order.client.service.OrderRestService;
import com.demo.order.client.util.TreeMapConvert;
import com.demo.order.object.OrderDetailQo;
import com.demo.order.object.OrderQo;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderFuture orderFuture;
    @Autowired
    private GoodsFuture goodsFuture;

    @Autowired
    private OrderRestService orderRestService;
    @Autowired
    private GoodsRestService goodsRestService;

    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping(value="/index")
    public ModelAndView index(ModelMap model) throws Exception{
        return new ModelAndView("order/index");
    }


    @RequestMapping(value="/{id}")
    public CompletableFuture<ModelAndView> findById(@PathVariable Long id) {
        return orderFuture.findById(id).thenApply(json -> {
            OrderQo orderQo = new Gson().fromJson(json, OrderQo.class);
            return new ModelAndView("order/show", "order", orderQo);
        });
    }


    @RequestMapping(value = "/list")
    public CompletableFuture<Page<Map<String, Object>>> findAll(OrderQo orderQo) {
        return orderFuture.findPage(orderQo).thenApply(json -> {
            logger.info("order list = {}", json);
            Gson gson = TreeMapConvert.getGson();
            TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

            Pageable pageable = new PageRequest(orderQo.getPage(), orderQo.getSize(), null);
            List<OrderQo> list = new ArrayList<>();

            if(page != null && page.get("content") != null)
                list = gson.fromJson(page.get("content").toString(), new TypeToken<List<OrderQo>>(){}.getType());
            String count = page.get("totalelements").toString();

            return new PageImpl(list, pageable, new Long(count));
        });
    }

    @RequestMapping(value="/verify")
    public ModelAndView verify(ModelMap model) throws Exception{
        return new ModelAndView("order/verify");
    }

    @RequestMapping(value="/switch")
    public ModelAndView toswitch(ModelMap model) throws Exception{
        return new ModelAndView("order/switch");
    }

    @RequestMapping(value="/accounts/{id}")
    public CompletableFuture<ModelAndView> accounts(ModelMap model, @PathVariable Long id) {
        return goodsFuture.findById(id).thenApply(json -> {
            GoodsQo goodsQo = new Gson().fromJson(json, GoodsQo.class);
            return new ModelAndView("order/accounts", "goods", goodsQo);
        });
    }

    @RequestMapping(value="/buyone", method = RequestMethod.POST)
    public CompletableFuture<String> buyone(OrderQo buyone) {
        return goodsFuture.findById(buyone.getId()).thenApply(json -> {
            GoodsQo goodsQo = new Gson().fromJson(json, GoodsQo.class);
            if(goodsQo != null){
                Integer sum = 1;
                OrderDetailQo orderDetailQo = new OrderDetailQo();
                orderDetailQo.setGoodsid(goodsQo.getId());
                orderDetailQo.setGoodsname(goodsQo.getName());
                orderDetailQo.setPrice(goodsQo.getPrice());
                orderDetailQo.setPhoto(goodsQo.getPhoto());
                orderDetailQo.setNums(sum);
                orderDetailQo.setMoney(sum * goodsQo.getPrice());

                OrderQo orderQo = new OrderQo();
                orderQo.addOrderDetail(orderDetailQo);
                orderQo.setUserid(buyone.getUserid());
                orderQo.setMerchantid(goodsQo.getMerchantid());
                orderQo.setAmount(sum * goodsQo.getPrice());
                orderQo.setOrderNo(new Long((new Date()).getTime()).toString());
                orderQo.setStatus(1);//待发货

                String sid = orderRestService.create(orderQo);

                if(sid != null && new Integer(sid) > 0) {
                    Integer buynum = goodsQo.getBuynum() == null ? sum : sum + goodsQo.getBuynum();
                    goodsQo.setBuynum(buynum);
                    goodsRestService.update(goodsQo);
                    return sid;
                }
            }
            return "-1";
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
