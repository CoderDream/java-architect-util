package com.debug.middleware.server.rabbitmq.publisher;/**
 * Created by Administrator on 2019/4/7.
 */

import com.debug.middleware.server.dto.UserLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 系统日志记录-生产者
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/7 19:18
 **/
@Component
public class LogPublisher {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(LogPublisher.class);
    //定义RabbitMQ操作组件
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //定义环境变量读取实例env
    @Autowired
    private Environment env;
    //定义Json序列化和反序列化组件
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 发送登录成功后的用户相关信息入队列
     * @param loginDto
     */
    public void sendLogMsg(UserLoginDto loginDto){
        try {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(env.getProperty("mq.login.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("mq.login.routing.key.name"));

            rabbitTemplate.convertAndSend(loginDto, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    MessageProperties messageProperties=message.getMessageProperties();
                    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,UserLoginDto.class);
                    return message;
                }
            });
            log.info("系统日志记录-生产者-发送登录成功后的用户相关信息入队列-内容：{} ",loginDto);
        }catch (Exception e){
            log.error("系统日志记录-生产者-发送登录成功后的用户相关信息入队列-发生异常：{} ",loginDto,e.fillInStackTrace());
        }
    }
}





























