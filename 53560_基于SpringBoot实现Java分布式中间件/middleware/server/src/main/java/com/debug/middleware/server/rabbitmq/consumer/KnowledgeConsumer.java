package com.debug.middleware.server.rabbitmq.consumer;/**
 * Created by Administrator on 2019/4/7.
 */

import com.debug.middleware.server.rabbitmq.entity.KnowledgeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 确认消费模式-消费者
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/7 8:29
 **/
@Component
public class KnowledgeConsumer {

    private static final Logger log= LoggerFactory.getLogger(KnowledgeConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 基于AUTO的确认消费模式-消费者-其中：
     * queues指的是监听的队列
     * containerFactory指的监听器所在的容器工厂-这在RabbitmqConfig中已经进行了AUTO消费模式的配置
     * @param msg
     */
    @RabbitListener(queues = "${mq.auto.knowledge.queue.name}",containerFactory = "singleListenerContainerAuto")
    public void consumeAutoMsg(@Payload byte[] msg){
        try {
            KnowledgeInfo info=objectMapper.readValue(msg, KnowledgeInfo.class);
            log.info("基于AUTO的确认消费模式-消费者监听消费消息-内容为：{} ",info);

        }catch (Exception e){
            log.error("基于AUTO的确认消费模式-消费者监听消费消息-发生异常：",e.fillInStackTrace());
        }
    }
}








































