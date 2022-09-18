package com.company.microservicedata.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;

/**
 * Direct交换机
 * @author xindaqi
 * @since 2020-12-02
 */
@Configuration
public class RabbitMessageQueueDirectConfig {
    @Bean
    public Queue directQueueString(){
        return new Queue("directQueueString");
    }

    @Bean
    public Queue directQueueObject() {
        return new Queue("directQueueObject");
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    Binding bindingDirectMessageString() {
        return BindingBuilder.bind(directQueueString()).to(directExchange()).with("drk.queue.string");
    }

    // @Bean
    // Binding bindingDirectMessageStringMirror() {
    //     return BindingBuilder.bind(directQueueObject()).to(directExchange()).with("drk.queue.string");
    // }

    @Bean
    Binding bindingDirectMessageObject() {
        return BindingBuilder.bind(directQueueObject()).to(directExchange()).with("drk.queue.object");
    }
}
