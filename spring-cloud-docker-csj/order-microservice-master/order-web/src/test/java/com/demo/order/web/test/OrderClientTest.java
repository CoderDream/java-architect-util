package com.demo.order.web.test;

import com.demo.order.client.service.OrderFuture;
import com.demo.order.client.util.TreeMapConvert;
import com.demo.order.object.OrderDetailQo;
import com.demo.order.object.OrderQo;
import com.demo.order.web.OrderWebApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {OrderWebApplication.class})
@SpringBootTest
public class OrderClientTest {
    private static Logger logger = LoggerFactory.getLogger(OrderClientTest.class);
    @Autowired
    private OrderFuture orderFuture;


    //@Test
    public void insertData(){
        OrderDetailQo orderDetail = new OrderDetailQo();
        orderDetail.setGoodsname("测试商品1");
        orderDetail.setGoodsid(1L);
        orderDetail.setPrice(12.20F);
        orderDetail.setNums(1);
        orderDetail.setMoney(12.20D);
        orderDetail.setPhoto("../images/demo1.png");

        OrderDetailQo orderDetail2 = new OrderDetailQo();
        orderDetail2.setGoodsname("测试商品2");
        orderDetail2.setGoodsid(2L);
        orderDetail2.setPrice(20.00F);
        orderDetail2.setNums(2);
        orderDetail2.setMoney(40.00D);
        orderDetail2.setPhoto("../images/demo2.png");

        OrderQo order = new OrderQo();
        order.setOrderNo("20170930000018");
        order.setUserid(11111235L);
        order.setAmount(52.20D);
        order.setStatus(1);
        order.addOrderDetail(orderDetail);
        order.addOrderDetail(orderDetail2);
        String sid = orderFuture.create(order).join();
        logger.info("========insert sid={}", sid);
        //Assert.isTrue(new Integer(sid) > 0, "not insert");
        assert new Integer(sid) > 0 : "not insert";
    }

    @Test
    public void update(){
        String json = orderFuture.findById(2L).join();
        OrderQo orderQo = new Gson().fromJson(json, OrderQo.class);
        orderQo.setStatus(-1);

        String sid = orderFuture.update(orderQo).join();
        logger.info("=======update sid={}", sid);
        assert new Integer(sid) > 0 : "not update";
    }

    //@Test
    public void delete(){
        String sid = orderFuture.delete(8L).join();
        logger.info("========delete sid={}", sid);
        assert new Integer(sid) > 0 : "not delete";
    }

    //@Test
    public void getPage() throws Exception{
        OrderQo orderQo = new OrderQo();
        String json = orderFuture.findPage(orderQo).join();

        Gson gson = TreeMapConvert.getGson();
        TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

        Pageable pageable = new PageRequest(orderQo.getPage(), orderQo.getSize(), null);

        List<OrderQo> list = new ArrayList<>();

        if(page.get("content") != null)
            list = gson.fromJson(page.get("content").toString(), new TypeToken<List<OrderQo>>(){}.getType());
        String count = page.get("totalelements").toString();

        Page<Map<String, Object>> newPage = new PageImpl(list, pageable, new Long(count));

        assert newPage.getTotalElements() > 0;
        for(OrderQo orders : list) {
            logger.info("===============orderno={}, goods name={}", orders.getOrderNo(), orders.getOrderDetails().iterator().next().getGoodsname());
        }
        logger.info("===============pageno={}, total_pages={}, total_elements={}", newPage.getNumber(), newPage.getTotalPages(), newPage.getTotalElements());
    }

}
