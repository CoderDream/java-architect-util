package com.coderdream.middleware.server.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * spring的事件驱动模型-生产者
 */
@Component
public class Publisher {
    /**
     * 定义日志
     */
    private static final Logger log = LoggerFactory.getLogger(Publisher.class);

    /**
     * 定义发送消息的组件
     */
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发送消息的方法
     *
     * @throws Exception
     */
    public void sendMsg() throws Exception {
        // 构造登陆
        LoginEvent event = new LoginEvent(this, "debug", new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss").format(new Date()), "127.0.0.1");
        applicationEventPublisher.publishEvent(event);
        log.info("Spring事件驱动模型-发送消息：{}", event);
    }

}