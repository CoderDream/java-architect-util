package com.demo.order.restapi.controller;

import com.demo.order.domain.entity.Order;
import com.demo.order.domain.entity.OrderDetail;
import com.demo.order.domain.service.OrderService;
import com.demo.order.domain.util.CommonUtils;
import com.demo.order.domain.util.CopyUtil;
import com.demo.order.object.OrderQo;
import com.demo.order.restapi.mqchannel.OutputSource;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.data.domain.Page;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
@EnableBinding(OutputSource.class)
public class OrderRestController {
    private static Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    @Output(OutputSource.ORDERSCHANNEL)
    private MessageChannel ordersChannel;


    @RequestMapping(value="/{id}")
    public CompletableFuture<String> fnidById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Order order = orderService.findOne(id);
            return new Gson().toJson(order);
        });
    }

    //test: local call
    @RequestMapping(value="/test/{id}")
    public String test(@PathVariable Long id) {
        Order order = orderService.findOne(id);
        return new Gson().toJson(order);
    }

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer index, Integer size, Long userid,
                                             Long merchantid, Integer status, String created) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                OrderQo orderQo = new OrderQo();

                if(CommonUtils.isNotNull(index)){
                    orderQo.setPage(index);
                }
                if(CommonUtils.isNotNull(size)){
                    orderQo.setSize(size);
                }
                if(CommonUtils.isNotNull(userid)){
                    orderQo.setUserid(userid);
                }
                if(CommonUtils.isNotNull(merchantid)){
                    orderQo.setMerchantid(merchantid);
                }
                if(CommonUtils.isNotNull(status)){
                    orderQo.setStatus(status);
                }
                if(CommonUtils.isNotNull(created)){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    orderQo.setCreated(sdf.parse(created));
                }

                Page<Order> orderses = orderService.findAll(orderQo);

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

    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody OrderQo orderQo) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Order order = CopyUtil.copy(orderQo, Order.class);

            List<OrderDetail> detailList = CopyUtil.copyList(orderQo.getOrderDetails(), OrderDetail.class);
            order.setOrderDetails(detailList);

            orderService.save(order);

            logger.info("新增->ID=" + order.getId());
            return order.getId().toString();
        });
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CompletableFuture<String> update(@RequestBody OrderQo orderQo) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            Order order = CopyUtil.copy(orderQo, Order.class);
            order.setModify(new Date());

            List<OrderDetail> detailList = CopyUtil.copyList(orderQo.getOrderDetails(), OrderDetail.class);
            order.setOrderDetails(detailList);

            orderService.save(order);

            //发送MQ消息，通知订单修改
            ordersChannel.send(MessageBuilder.withPayload(orderQo).build());

            logger.info("修改->ID=" + order.getId());
            return order.getId().toString();
        });
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            orderService.delete(id);

            logger.info("删除->ID=" + id);
            return id.toString();
        });
    }

}
