package com.demo.goods.restapi.test;


import com.demo.goods.domain.config.JpaConfiguration;
import com.demo.goods.domain.entity.Goods;
import com.demo.goods.domain.service.GoodsService;
import com.demo.goods.object.GoodsQo;
import com.demo.goods.restapi.GoodsRestApiApplication;
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
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class, GoodsRestApiApplication.class})
@SpringBootTest
public class GoodsTest {
    private static Logger logger = LoggerFactory.getLogger(GoodsTest.class);
    @Autowired
    private GoodsService goodsService;


    //@Test
    public void insertData(){
        Goods goods = new Goods();
        goods.setMerchantid(1L);
        goods.setSortsid(1L);
        goods.setSubsid(1L);
        goods.setName("测试商品1");
        goods.setPhoto("../images/demo1.png");
        goods.setPrice(11.2f);
        goods.setContents("商品介绍");

        goodsService.save(goods);
        
        Assert.notNull(goods.getId(), "not insert");
    }

    //@Test
    public void getData(){
        Goods goods = goodsService.findOne(1L);
        logger.info("===============id="+ goods.getId());
        Assert.notNull(goods, "get null");
    }

    //@Test
    public void delData(){
        goodsService.delete(2L);
    }

    //@Test
    public void updateData(){
        Goods goods = goodsService.findOne(1L);
        Assert.notNull(goods, "get null");
        goods.setName("测试商品");
        goodsService.save(goods);
    }


    @Test
    public void findAll() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-01-01 00:00:00");

        GoodsQo goodsQo = new GoodsQo();
        goodsQo.setName("测试");
        goodsQo.setCreated(date);

        Page<Goods> page = goodsService.findAll(goodsQo);

        List<Goods> list = page.getContent();

        Assert.notEmpty(list, "list is empty");

        for(Goods goods : list) {
            logger.info("===============goods merchantid={}, goods name = {}",
                    goods.getMerchantid(), goods.getName());
        }

    }
}
