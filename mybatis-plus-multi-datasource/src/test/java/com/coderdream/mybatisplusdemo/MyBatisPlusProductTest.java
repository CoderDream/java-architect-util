package com.coderdream.mybatisplusdemo;

import com.coderdream.mybatisplusdemo.entity.Product;
import com.coderdream.mybatisplusdemo.entity.User;
import com.coderdream.mybatisplusdemo.mapper.ProductMapper;
import com.coderdream.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusProductTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testProduct1() {

        //1、小李
        Product p1 = productMapper.selectById(1L);
        System.out.println("小李取出的价格：" + p1.getPrice());

        //2、小王
        Product p2 = productMapper.selectById(1L);
        System.out.println("小王取出的价格：" + p2.getPrice());

        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);// 100 + 50
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);

        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);// 100 -30
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改结果：" + result2);

        //最后的结果 老板查询
        Product p3 = productMapper.selectById(1L);

        //价格覆盖，最后的结果：70
        System.out.println("最后的结果：" + p3.getPrice());
    }

    //testProduct简单优化后
    @Test
    public void testProductUpdate() {
        //1、小李
        Product p1 = productMapper.selectById(1L);
        System.out.println("小李取出的价格：" + p1.getPrice());

        //2、小王
        Product p2 = productMapper.selectById(1L);
        System.out.println("小王取出的价格：" + p2.getPrice());

        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);// 100 + 50
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);

        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);// 100 -30
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改结果：" + result2);

        //改良
        if (result2 == 0) {
            //操作失败，重新获得版本号
            Product productNew = productMapper.selectById(1L);
            productNew.setPrice(productNew.getPrice() - 30);
            int resultNew = productMapper.updateById(productNew);
            System.out.println("如果出现问题，最后小王的修改结果：" + resultNew);
        }

        //最后的结果 老板查询
        Product p3 = productMapper.selectById(1L);

        //价格覆盖，最后的结果：70
        System.out.println("最后的结果：" + p3.getPrice());
    }
}
