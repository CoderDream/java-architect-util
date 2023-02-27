package com.coderdream.mybatisplusdemo;

import com.coderdream.mybatisplusdemo.service.ProductService;
import com.coderdream.mybatisplusdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusDatasourceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Test
    public void testDynamicDataSource() {
        System.out.println("查找id为1的数据：" + userService.getById(1L)); //通用service的查询都是get开头  数据已经逻辑删除 搜索不到
        System.out.println("查找id为2的数据：" + userService.getById(2L)); //通用service的查询都是get开头
        System.out.println("查找id为1的数据：" + productService.getById(1L));//通用service的查询都是get开头
    }
}
