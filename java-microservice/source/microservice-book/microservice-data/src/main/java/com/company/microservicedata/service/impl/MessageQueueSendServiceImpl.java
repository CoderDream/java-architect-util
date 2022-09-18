package com.company.microservicedata.service.impl;

import com.company.microservicedata.dto.MessageQueueInputDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.Channel;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.microservicedata.service.IMessageQueueSendService;

/**
 * 消息生产者
 * @author xindaqi
 * @since 2020-12-03
 */
@Component
public class MessageQueueSendServiceImpl implements IMessageQueueSendService {
    
    private static Logger logger = LoggerFactory.getLogger(MessageQueueSendServiceImpl.class);

    @Autowired 
    private AmqpTemplate template;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void directSendString(String params){
        logger.info("Direct开始发送String消息");
        rabbitTemplate.convertAndSend("directExchange", "drk.queue.string", params, message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        }, new CorrelationData(UUID.randomUUID().toString()));
    }

    @Override
    public void directSendObject(MessageQueueInputDTO params){
        logger.info("Direct开始发送Object消息");
        rabbitTemplate.convertAndSend("directExchange", "drk.queue.object", params, new CorrelationData(UUID.randomUUID().toString()));
    }

    @Override
    public void topicOneSend(String params){
        logger.info("Topic一对一并转发消息");
        rabbitTemplate.convertAndSend("topicExchange", "trk.queue_1", params, new CorrelationData(UUID.randomUUID().toString()));
    }

    @Override
    public void topicTwoSend(String params){
        logger.info("Topic一对一并转发消息");
        rabbitTemplate.convertAndSend("topicExchange", "trk.queue_2", params, new CorrelationData(UUID.randomUUID().toString()));
    }

    @Override
    public void topicManySend(String params){
        logger.info("Topic一对多汇集并消息");
        rabbitTemplate.convertAndSend("topicExchange", "trk.#", params, new CorrelationData(UUID.randomUUID().toString()));
    }

    @Override
    public void fanoutSend(String params) {
        logger.info("Fanout发送消息");
        rabbitTemplate.convertAndSend("fanoutExchange", "", params, new CorrelationData(UUID.randomUUID().toString()));
    }


}
