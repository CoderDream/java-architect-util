package com.company.microservicedata.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout交换机
 * @author xindaqi
 * @since 2020-12-06
 */
@Configuration
public class RabbitMessageQueueFanoutConfig {

    @Bean
    public Queue fanoutQueueOne() {
        return new Queue("fanoutQueueOne");
    }

    @Bean
    public Queue fanoutQueueTwo() {
        return new Queue("fanoutQueueTwo");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingFanoutMessageOne(Queue fanoutQueueOne, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueOne).to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutMessageTwo(Queue fanoutQueueTwo, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueTwo).to(fanoutExchange);
    }

}
