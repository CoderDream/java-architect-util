package com.company.microservicedata.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import com.rabbitmq.client.Channel;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.microservicedata.service.IMessageQueueReceiveService;

/**
 *  消息消费者
 * @author xindaqi
 * @since 2020-12-03
 */
@Component
public class MessageQueueReceiveServiceImpl implements IMessageQueueReceiveService {
    
    private static Logger logger = LoggerFactory.getLogger(MessageQueueReceiveServiceImpl.class);
    
    @Autowired
    private AmqpTemplate template;
    @Autowired 
    private RabbitTemplate rabbitTempalte;

    @Override
    @RabbitListener(queues="directQueueString")
    @RabbitHandler
    public void directReceiveString(Message msg, Channel channel) throws IOException {
        try {
            logger.info("接收成功--Direct String交换机: {}", msg);
            //TODO 写入数据库等其他操作
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            logger.info("接收失败--Direct String交换机:{}", e);
        }
    }

    @Override
    @RabbitListener(queues="directQueueObject")
    public void directReceiveObject(Message msg, Channel channel) throws IOException {
        try {
            logger.info("接收成功--Direct Object交换机: {}", msg);
            //TODO 写入数据库等其他操作
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            logger.info("接收失败--Direct Object交换机:{}", e);
        }
    }

    @Override
    @RabbitListener(queues="queue_1")
    @RabbitHandler
    public void topicOneReceive(Message msg, Channel channel) throws IOException {
        try {
            logger.info("接收成功--Topic交换机queue_1: {}", msg);
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            logger.info("接收失败: {}", e);
        }
    }

    @Override
    @RabbitListener(queues="queue_2")
    public String topicTwoReceive(Message msg, Channel channel) throws IOException {
        try {
            logger.info("接收成功--Topic交换机queue_2: {}", msg);
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
            return new String(msg.getBody(), "UTF-8");
        } catch (Exception e) {
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            logger.info("接收失败: {}", e);
        }
        return "ERROR";
    }

    @Override
    @RabbitListener(queues="queue_n")
    public void topicManyReceive(Message msg, Channel channel) throws IOException {
        try {
            logger.info("接收成功--Topic交换机queue_n: {}", msg);
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch(Exception e) {
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            logger.info("接收失败: {}", e);
        }
    }

    @Override
    @RabbitListener(queues="fanoutQueueOne")
    public void fanoutOneReceive(Message msg, Channel channel) throws IOException {
        try {
            logger.info("接收成功--Fanout交换机fanoutQueueOne: {}", msg);
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch(Exception e) {
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            logger.info("接收失败: {}", e);
        }
    }

    @Override
    @RabbitListener(queues="fanoutQueueTwo")
    public void fanoutTwoReceive(Message msg, Channel channel) throws IOException {
        try {
            logger.info("接收成功--Fanout交换机fanoutQueueTwo: {}", msg);
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch(Exception e) {
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            logger.info("接收失败: {}", e);
        }
    }



}
