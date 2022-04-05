package com.debug.middleware.server;/**
 * Created by Administrator on 2019/3/29.
 */

import com.debug.middleware.server.MainApplication;
import com.debug.middleware.server.event.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/29 16:10
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EventTest {

    @Autowired
    private Publisher publisher;

    @Test
    public void test1() throws Exception{
        publisher.sendMsg();
    }

}