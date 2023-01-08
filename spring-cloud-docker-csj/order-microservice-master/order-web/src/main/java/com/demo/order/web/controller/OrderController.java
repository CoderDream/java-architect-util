package com.demo.order.web.controller;

import com.demo.order.client.service.OrderFuture;
import com.demo.order.client.util.TreeMapConvert;
import com.demo.order.object.OrderQo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderFuture orderFuture;

    @RequestMapping(value="/index")
    public ModelAndView index(ModelMap model) throws Exception{//, Principal user
        //model.addAttribute("user", user);
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

}
