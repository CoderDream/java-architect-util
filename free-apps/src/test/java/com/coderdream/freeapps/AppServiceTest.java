package com.coderdream.freeapps;

import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
@SpringBootTest
public class AppServiceTest {

    @Autowired
    private AppService appService; //这里可能爆红，但是运行没问题

//    @Test
//    public void testGetCount() {
//        //查询总记录数
//        //SELECT COUNT( * ) FROM user
//        long count = userService.count();
//        System.out.println("总记录数：" + count);
//    }
//
    @Test
    public void testInsert() {
        //批量添加
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        List<App> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            App app = new App();
            app.setAppId("user" + i);

            int b = appService.insertSelective(app);  //boolean 操作是否成功
            System.out.println("结果：" + b);
        }
    }

    @Test
    public void testInsertOrUpdateBatch() {
        //批量添加
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        List<App> list = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            App app = new App();
//            app.setAppId("id" +  String.format("%09d",  new Random().nextInt(999999999)));
            app.setAppId("id" +  String.format("%09d",  new Random().nextInt(99)));
            app.setName("name" + new Random().nextInt(100));
            list.add(app);
        }
        int b = appService.insertOrUpdateBatch(list);  //boolean 操作是否成功
        System.out.println("结果：" + b);
    }
}
