package com.debug.middleware.server.event;/**
 * Created by Administrator on 2019/3/29.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/29 16:03
 **/
@Component
@EnableAsync
public class Consumer implements ApplicationListener<LoginEvent>{

    private static final Logger log= LoggerFactory.getLogger(Consumer.class);

    /**
     * 监听消费消息-事件信息
     * @param loginEvent
     */
    @Override
    @Async
    public void onApplicationEvent(LoginEvent loginEvent) {
        log.info("Spring事件驱动模型-接收消息：{}",loginEvent);

        //TODO:后续为实现自身的业务需求-比如写入数据库等等

    }
}













