package com.coderdream.demo;

import com.coderdream.demo.dao.UserDao;
import com.coderdream.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    // 注入Dao接口,继承BaseMapper
    @Resource
    private UserDao userDao;

    @Test
    void contextLoads() {
        // 调用BaseMapper查询方法<selectList>
        List<User> list = userDao.selectList(null);
        list.forEach(System.out::println);
    }

}
