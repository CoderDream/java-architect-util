package com.coderdream.freeapps;

import com.coderdream.freeapps.model.User;
import com.coderdream.freeapps.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;

//    @Test
//    public void testGetCount() {
//        //查询总记录数
//        //SELECT COUNT( * ) FROM user
//        long count = userService.count();
//        System.out.println("总记录数：" + count);
//    }
//
//    @Test
//    public void testInsertMore() {
//        //批量添加
//        //INSERT INTO user ( id,title, age, email ) VALUES ( ?, ?, ?, ? )
//        List<User> list = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            User user = new User();
//            user.setName("user" + i);
//            user.setAge(20 + i);
//            user.setEmail("user" + i + "@163.com");
//            list.add(user);
//        }
//        boolean b = userService.saveBatch(list);  //boolean 操作是否成功
//        System.out.println("结果：" + b);
//    }
}
