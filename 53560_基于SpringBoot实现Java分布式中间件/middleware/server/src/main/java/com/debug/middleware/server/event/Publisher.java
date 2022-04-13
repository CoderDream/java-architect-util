package com.debug.middleware.server.event;/**
 * Created by Administrator on 2019/3/29.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * spring的事件驱动模型-生产者
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/29 15:59
 **/
@Component
public class Publisher {

    private static final Logger log= LoggerFactory.getLogger(Publisher.class);

    @Autowired
    private ApplicationEventPublisher publisher;

    public void sendMsg() throws Exception{
        LoginEvent event=new LoginEvent(this,"debug",new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss").format(new Date()),"127.0.0.1");
        publisher.publishEvent(event);
        log.info("Spring事件驱动模型-发送消息：{}",event);
    }

}