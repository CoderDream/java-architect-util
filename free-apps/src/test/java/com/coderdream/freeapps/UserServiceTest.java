package com.coderdream.freeapps;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.freeapps.mapper.UserMapper;
import com.coderdream.freeapps.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {
        // SELECT id,user_name AStitle,age,email,is_deleted FROM user WHERE is_deleted=0 LIMIT ?,?
        Page<User> page = new Page<>(2, 3);//这里有 limit 后面两个参数 当前也起始索引index pageSize每页显示的条数
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
}
