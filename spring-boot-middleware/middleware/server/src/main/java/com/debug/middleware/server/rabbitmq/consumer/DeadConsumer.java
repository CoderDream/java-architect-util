package com.debug.middleware.server.rabbitmq.consumer;/**
 * Created by Administrator on 2019/4/9.
 */

import com.debug.middleware.server.rabbitmq.entity.DeadInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 死信队列-真正队列消费者
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/9 20:42
 **/
@Component
public class DeadConsumer {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(DeadConsumer.class);
    //定义Json序列化和反序列化组件
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 监听真正队列-消费队列中的消息 - 面向消费者
     * @param info
     */
    @RabbitListener(queues = "${mq.consumer.real.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMsg(@Payload DeadInfo info){
        try {
            log.info("死信队列实战-监听真正队列-消费队列中的消息,监听到消息内容为：{}",info);

            //TODO:用于执行后续的相关业务逻辑

        }catch (Exception e){
            log.error("死信队列实战-监听真正队列-消费队列中的消息 - 面向消费者-发生异常：{} ",info,e.fillInStackTrace());
        }
    }

}









































