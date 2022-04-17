package com.debug.middleware.server.service.redisson.delayQueue;/**
 * Created by Administrator on 2019/5/2.
 */

import com.debug.middleware.server.dto.DeadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ死信队列消息模型-生产者
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/2 17:10
 **/
@Component
public class MqDelayQueuePublisher {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(MqDelayQueuePublisher.class);
    //定义RabbitMQ发送消息的操作组件实例
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //定义读取环境变量的实例env
    @Autowired
    private Environment env;

    /**
     * 发送消息入延迟队列
     * @param msg 消息
     * @param ttl 消息的存活时间
     */
    public void sendDelayMsg(final DeadDto msg, final Long ttl){
        try {
            //设置消息在RabbitMQ传输的格式
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            //设置消息进入的死信队列
            rabbitTemplate.setExchange(env.getProperty("mq.redisson.dead.basic.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("mq.redisson.dead.basic.routing.key.name"));
            //调用RabbitMQ操作组件发送消息的方法
            rabbitTemplate.convertAndSend(msg, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    //获取消息属性实例
                    MessageProperties messageProperties=message.getMessageProperties();
                    //设置消息的持久化
                    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    //指定消息头中消息的具体类型
                    messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,DeadDto.class);
                    //设置消息的过期时间
                    messageProperties.setExpiration(ttl.toString());
                    //返回消息实例
                    return message;
                }
            });

            log.info("RabbitMQ死信队列消息模型-生产者-发送消息入延迟队列-消息：{}",msg);
        }catch (Exception e){
            log.error("RabbitMQ死信队列消息模型-生产者-发送消息入延迟队列-发生异常：{}",msg,e.fillInStackTrace());
        }
    }
}
























































