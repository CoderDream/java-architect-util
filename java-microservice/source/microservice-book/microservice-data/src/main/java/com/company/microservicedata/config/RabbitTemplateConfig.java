package com.company.microservicedata.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitTemplate配置
 * @author xindaqi
 * @since 2020-12-06
 */
@Configuration
public class RabbitTemplateConfig {

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageQueueConfirmAndReturnConfig messageQueueConfirmAndReturnConfig) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setConfirmCallback(messageQueueConfirmAndReturnConfig);
        rabbitTemplate.setReturnCallback(messageQueueConfirmAndReturnConfig);
        /**
         * Mandatory为true时，callback和return生效
         * 消息通过交换器无法匹配到队列会返回给生产者，
         * 为false时，匹配不到队列，直接丢弃消息
         */
        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setMessageConverter();
        return rabbitTemplate;
    }
}
