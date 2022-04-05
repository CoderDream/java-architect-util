package com.debug.middleware.server.rabbitmq.publisher;/**
 * Created by Administrator on 2019/4/7.
 */

import com.debug.middleware.server.rabbitmq.entity.KnowledgeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 确认消费模式-手动确认消费-生产者
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/7 8:29
 **/
@Component
public class KnowledgeManualPublisher {

    private static final Logger log= LoggerFactory.getLogger(KnowledgeManualPublisher.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment env;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 基于MANUAL机制-生产者发送消息
     * @param info
     */
    public void sendAutoMsg(KnowledgeInfo info){
        try {
            if (info!=null){
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("mq.manual.knowledge.exchange.name"));
                rabbitTemplate.setRoutingKey(env.getProperty("mq.manual.knowledge.routing.key.name"));

                Message message=MessageBuilder.withBody(objectMapper.writeValueAsBytes(info))
                        .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                        .build();
                rabbitTemplate.convertAndSend(message);
                log.info("基于MANUAL机制-生产者发送消息-内容为：{} ",info);
            }
        }catch (Exception e){
            log.error("基于MANUAL机制-生产者发送消息-发生异常：{} ",info,e.fillInStackTrace());
        }
    }
}
































