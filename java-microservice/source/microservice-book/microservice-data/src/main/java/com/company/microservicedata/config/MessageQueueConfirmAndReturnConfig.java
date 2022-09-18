package com.company.microservicedata.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.stereotype.Component;


/**
 * RabbitTemplate发送消息至Exchange和Queue消息回调
 * @author xindaqi
 * @since 2020-12-06
 */
@Component
public class MessageQueueConfirmAndReturnConfig implements ConfirmCallback, ReturnCallback {

    private static Logger logger = LoggerFactory.getLogger(MessageQueueConfirmAndReturnConfig.class);


    /**
     * confirm机制触发条件：消息-》exchange（而不是到queue触发）
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(!ack) {
            logger.error("MQ消息送达exchange异常，原因：{}", cause);
            logger.error("MQ消息唯一标识：{}", correlationData);
        }else {
            logger.info("MQ消息送达exchange");
            logger.info("MQ消息唯一标识：{}", correlationData);
        }
    }

    /**
     * return触发条件：消息无法发送到队列，exchange不存在或指定routingKey不存在
     * @param message
     * @param replyCode
     * @param replyString
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyString, String exchange, String routingKey) {
        logger.error("消息不可达：{}", message);
    }
}
