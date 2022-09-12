package com.coderdream.mybatisplusdemo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.mybatisplusdemo.mapper.ProductMapper;
import com.coderdream.mybatisplusdemo.mapper.UserMapper;
import com.coderdream.mybatisplusdemo.entity.Product;
import com.coderdream.mybatisplusdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusPluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test1() {
        // SELECT id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted=0 LIMIT ?,?
        Page<User> page = new Page<User>(2, 3);//这里有 limit 后面两个参数 当前也起始索引index pageSize每页显示的条数
        userMapper.selectPage(page, null);//selectPage方法有两个参数，第一个分页对象，第二个参数Wrapper条件构造器
        System.out.println(page);
        System.out.println("page：" + page);
        System.out.println("当前页信息：getRecords()：" + page.getRecords()); //获取当前页数据信息
        System.out.println("当前页页码：getCurrent()：" + page.getCurrent()); // 获取当前页页码
        System.out.println("每页显示的条数：getSize()：" + page.getSize());// 获得当前页条数
        System.out.println("总页数：getPages()：" + page.getPages());//获取总页数，不包含逻辑删除的数据
        System.out.println("总记录数：getTotal()：" + page.getTotal());//获取总记录数
        System.out.println("是否有上一页：hasNext()：" + page.hasNext());//判断有没有下一页
        System.out.println("是否有下一页：hasPrevious()：" + page.hasPrevious());//判断有没有上一页
    }

    @Test
    public void testVo() {
        Page<User> page = new Page<User>(1, 3);
        userMapper.selectPageVo(page, 20);
        System.out.println("page：" + page);
        System.out.println("当前页信息：getRecords()：" + page.getRecords()); //获取当前页数据信息
        System.out.println("当前页页码：getCurrent()：" + page.getCurrent()); // 获取当前页页码
        System.out.println("每页显示的条数：getSize()：" + page.getSize());// 获得当前页条数
        System.out.println("总页数：getPages()：" + page.getPages());//获取总页数，不包含逻辑删除的数据
        System.out.println("总记录数：getTotal()：" + page.getTotal());//获取总记录数
        System.out.println("是否有上一页：hasNext()：" + page.hasNext());//判断有没有下一页
        System.out.println("是否有下一页：hasPrevious()：" + page.hasPrevious());//判断有没有上一页
    }

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
    }
}
