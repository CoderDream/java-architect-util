package com.debug.middleware.server.rabbitmq.consumer;/**
 * Created by Administrator on 2019/4/7.
 */

import com.debug.middleware.server.rabbitmq.entity.KnowledgeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 确认消费模式-人为手动确认消费-监听器
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/7 11:15
 **/
@Component("knowledgeManualConsumer")
public class KnowledgeManualConsumer implements ChannelAwareMessageListener{

    private static final Logger log= LoggerFactory.getLogger(KnowledgeManualConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 监听消费消息
     * @param message 消息实体
     * @param channel 通道
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MessageProperties messageProperties=message.getMessageProperties();
        long deliveryTag=messageProperties.getDeliveryTag();

        try {
            byte[] msg=message.getBody();
            KnowledgeInfo info=objectMapper.readValue(msg, KnowledgeInfo.class);
            log.info("确认消费模式-人为手动确认消费-监听器监听消费消息-内容为：{} ",info);

            //第一个参数为：消息的分发标识(唯一);第二个参数：是否允许批量确认消费(在这里设置为true)
            channel.basicAck(deliveryTag,true);
        }catch (Exception e){
            log.info("确认消费模式-人为手动确认消费-监听器监听消费消息-发生异常：",e.fillInStackTrace());

            //如果在处理消息的过程中发生了异常,则照样需要人为手动确认消费掉该消息
            // (否则该消息将一直留在队列中,从而将导致消息的重复消费)
            channel.basicReject(deliveryTag,false);
        }
    }
}




























