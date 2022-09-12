package com.coderdream.mybatisplusdemo;

import com.coderdream.mybatisplusdemo.enums.SexEnum;
import com.coderdream.mybatisplusdemo.mapper.UserMapper;
import com.coderdream.mybatisplusdemo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = new User();
        user.setId(8L);//这里我固定设值了，如果是自增不需要设置
        user.setName("admin");
        user.setAge(33);
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println("result：" + result);
    }
}
