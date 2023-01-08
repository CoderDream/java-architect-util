package com.demo.goods.restapi.service;

import com.demo.goods.domain.entity.Goods;
import com.demo.goods.domain.service.GoodsService;
import com.demo.goods.restapi.mqchannel.InputSource;
import com.demo.order.object.OrderDetailQo;
import com.demo.order.object.OrderQo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@EnableBinding(InputSource.class)
public class OrdersReceive {
    private static Logger logger = LoggerFactory.getLogger(OrdersReceive.class);
    @Autowired
    private GoodsService goodsService;

    @ServiceActivator(inputChannel = InputSource.ORDERSCHANNEL, outputChannel = InputSource.REPLYCHANNEL)
    public CompletableFuture<String> accept(OrderQo orderQo) {
        return CompletableFuture.supplyAsync(() -> {
            if(orderQo != null) {
                logger.info("接收到订单更新消息，订单编号=" + orderQo.getOrderNo());

                if (orderQo.getStatus() != null && orderQo.getStatus() < 0) {
                    List<OrderDetailQo> list = orderQo.getOrderDetails();
                    for (OrderDetailQo orderDetailQo : list) {
                        Goods goods = goodsService.findOne(orderDetailQo.getGoodsid());
                        if(goods != null){
                            Integer num  = goods.getBuynum() != null && goods.getBuynum() >0?
                                    goods.getBuynum() - 1 : 0;
                            goods.setBuynum(num);
                            goodsService.save(goods);
                            logger.info("更新了商品购买数量，商品名称=" + goods.getName());
                        }
                    }
                }
            }
            return "1";
        });
    }
}
