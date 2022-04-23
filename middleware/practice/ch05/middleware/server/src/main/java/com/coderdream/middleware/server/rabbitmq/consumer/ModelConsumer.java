package com.coderdream.middleware.server.rabbitmq.consumer;

import com.coderdream.middleware.server.rabbitmq.entity.EventInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 消息模型-消费者
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/31 21:52
 **/
@Component
public class ModelConsumer {

    private static final Logger log = LoggerFactory.getLogger(ModelConsumer.class);

    @Resource
    public ObjectMapper objectMapper;

    /**
     * 监听并消费队列中的消息-fanoutExchange-one
     */
    @RabbitListener(queues = "${mq.fanout.queue.one.name}", containerFactory = "singleListenerContainer")
    public void consumeFanoutMsgOne(@Payload byte[] msg) {
        try {
            EventInfo info = objectMapper.readValue(msg, EventInfo.class);
            log.info("消息模型fanoutExchange-one-消费者-监听消费到消息：{} ", info);


        } catch (Exception e) {
            log.error("消息模型-消费者-发生异常：", e.fillInStackTrace());
        }
    }

    /**
     * 监听并消费队列中的消息-fanoutExchange-two
     */
    @RabbitListener(queues = "${mq.fanout.queue.two.name}", containerFactory = "singleListenerContainer")
    public void consumeFanoutMsgTwo(@Payload byte[] msg) {
        try {
            EventInfo info = objectMapper.readValue(msg, EventInfo.class);
            log.info("消息模型fanoutExchange-two-消费者-监听消费到消息：{} ", info);


        } catch (Exception e) {
            log.error("消息模型-消费者-发生异常：", e.fillInStackTrace());
        }
    }


    /**
     * 监听并消费队列中的消息-directExchange-one
     */
    @RabbitListener(queues = "${mq.direct.queue.one.name}", containerFactory = "singleListenerContainer")
    public void consumeDirectMsgOne(@Payload byte[] msg) {
        try {
            EventInfo info = objectMapper.readValue(msg, EventInfo.class);
            log.info("消息模型directExchange-one-消费者-监听消费到消息：{} ", info);


        } catch (Exception e) {
            log.error("消息模型directExchange-one-消费者-监听消费发生异常：", e.fillInStackTrace());
        }
    }

    /**
     * 监听并消费队列中的消息-directExchange-two
     */
    @RabbitListener(queues = "${mq.direct.queue.two.name}", containerFactory = "singleListenerContainer")
    public void consumeDirectMsgTwo(@Payload byte[] msg) {
        try {
            EventInfo info = objectMapper.readValue(msg, EventInfo.class);
            log.info("消息模型directExchange-two-消费者-监听消费到消息：{} ", info);


        } catch (Exception e) {
            log.error("消息模型directExchange-two-消费者-监听消费发生异常：", e.fillInStackTrace());
        }
    }


    /**
     * 监听并消费队列中的消息-topicExchange-*通配符
     */
    @RabbitListener(queues = "${mq.topic.queue.one.name}", containerFactory = "singleListenerContainer")
    public void consumeTopicMsgOne(@Payload byte[] msg) {
        try {
            String message = new String(msg, StandardCharsets.UTF_8);
            log.info("消息模型topicExchange-*-消费者-监听消费到消息：{} ", message);


        } catch (Exception e) {
            log.error("消息模型topicExchange-*-消费者-监听消费发生异常：", e.fillInStackTrace());
        }
    }


    /**
     * 监听并消费队列中的消息-topicExchange-#通配符
     */
    @RabbitListener(queues = "${mq.topic.queue.two.name}", containerFactory = "singleListenerContainer")
    public void consumeTopicMsgTwo(@Payload byte[] msg) {
        try {
            String message = new String(msg, StandardCharsets.UTF_8);
            log.info("消息模型topicExchange-#-消费者-监听消费到消息：{} ", message);


        } catch (Exception e) {
            log.error("消息模型topicExchange-#-消费者-监听消费发生异常：", e.fillInStackTrace());
        }
    }

}





















