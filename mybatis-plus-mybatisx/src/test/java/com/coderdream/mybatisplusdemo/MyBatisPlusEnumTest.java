package com.coderdream.mybatisplusdemo;

import com.coderdream.mybatisplusdemo.enums.SexEnum;
import com.coderdream.mybatisplusdemo.mapper.User2Mapper;
import com.coderdream.mybatisplusdemo.entity.User2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusEnumTest {

    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void test() {
        User2 user2 = new User2();
        user2.setId(18L);//这里我固定设值了，如果是自增不需要设置
        user2.setName("admin");
        user2.setAge(33);
//        user2.setSex(SexEnum.MALE);
        int result = user2Mapper.insert(user2);
        System.out.println("result：" + result);
    }
}
