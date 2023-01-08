package com.demo.order.restapi.test;


import com.demo.order.domain.config.JpaConfiguration;
import com.demo.order.domain.entity.Order;
import com.demo.order.domain.entity.OrderDetail;
import com.demo.order.domain.service.OrderService;
import com.demo.order.object.OrderQo;
import com.demo.order.restapi.OrderRestApiApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class, OrderRestApiApplication.class})
@SpringBootTest
public class OrderTest {
    private static Logger logger = LoggerFactory.getLogger(OrderTest.class);
    @Autowired
    private OrderService ordersService;

    //@Test
    public void insertData(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setGoodsname("测试商品1");
        orderDetail.setGoodsid(1L);
        orderDetail.setPrice(12.20F);
        orderDetail.setNums(1);
        orderDetail.setMoney(12.20D);
        orderDetail.setPhoto("../images/demo1.png");

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setGoodsname("测试商品2");
        orderDetail2.setGoodsid(2L);
        orderDetail2.setPrice(20.00F);
        orderDetail2.setNums(2);
        orderDetail2.setMoney(40.00D);
        orderDetail2.setPhoto("../images/demo2.png");

        Order order = new Order();
        order.setOrderNo("20170930000004");
        order.setUserid(11111235L);
        order.setAmount(52.20D);
        order.setStatus(1);
        order.addOrderDetails(orderDetail);
        order.addOrderDetails(orderDetail2);
        ordersService.save(order);
        Assert.notNull(order.getId(), "not insert");
    }

    //@Test
    public void getData(){
        Order order = ordersService.findOne(1L);
        logger.info("===============id="+ order.getId());
        Assert.notNull(order, "order null");
    }

    //@Test
    public void delData(){
        ordersService.delete(1L);
        //Assert.notNull(order, "not delete");
    }



    //@Test
    public void updateData(){
        Order order = ordersService.findOne(2L);
        Assert.notNull(order, "order null");

        order.setStatus(1);
        ordersService.save(order);

        logger.info("=========update order id="+ order.getId());
    }


    @Test
    public void findAll() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-01-01 00:00:00");
        OrderQo orderQo = new OrderQo();
//        orderQo.setUserid(12345678901L);
//        orderQo.setStatus(1);
//        orderQo.setCreated(date);
        Page<Order> page = ordersService.findAll(orderQo);

        Assert.notEmpty(page.getContent(), "list is empty");

        for(Order order : page.getContent()) {
            logger.info("=========order id={}, order userid={}, detail goods name = {}",
                    order.getId(), order.getUserid(), order.getOrderDetails().iterator().next().getGoodsname());
        }

    }
}
