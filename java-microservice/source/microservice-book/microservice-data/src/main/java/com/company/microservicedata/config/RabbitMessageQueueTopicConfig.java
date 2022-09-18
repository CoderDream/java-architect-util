package com.company.microservicedata.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;

/**
 * Topic交换机
 * @author xindaqi
 * @since 2020-12-02
 */
@Configuration
public class RabbitMessageQueueTopicConfig {
    @Bean
    public Queue topicQueueA(){
        return new Queue("queue_1");
    }

    @Bean
    public Queue topicQueueB(){
        return new Queue("queue_2");
    }

    @Bean
    public Queue topicQueueN(){
        return new Queue("queue_n");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean 
    Binding bindingExchangeMessageOne(Queue topicQueueA, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueA).to(topicExchange).with("trk.queue_1");
    }

    @Bean 
    Binding bindingExchangeMessageTwo(Queue topicQueueB, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueB).to(topicExchange).with("trk.queue_2");
    }

    @Bean
    Binding bindExchangeMessageMany(Queue topicQueueN, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueN).to(topicExchange).with("trk.#");
    }
}