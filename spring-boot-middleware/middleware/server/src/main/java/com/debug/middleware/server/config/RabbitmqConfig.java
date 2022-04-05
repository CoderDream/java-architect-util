package com.debug.middleware.server.config;

import com.debug.middleware.server.rabbitmq.consumer.KnowledgeManualConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ自定义注入Bean配置
 * Created by steadyjack on 2019/3/30.
 */
@Configuration
public class RabbitmqConfig {

    private static final Logger log= LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 单一消费者
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        return factory;
    }

    /**
     * 多个消费者
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory,connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(10);
        factory.setMaxConcurrentConsumers(15);
        factory.setPrefetchCount(10);
        return factory;
    }

    /**
     * RabbitMQ发送消息的操作组件实例
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(){
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }

    //定义读取配置文件的环境变量实例
    @Autowired
    private Environment env;

    /**创建简单消息模型：队列、交换机和路由 **/

    //创建队列
    @Bean(name = "basicQueue")
    public Queue basicQueue(){
        return new Queue(env.getProperty("mq.basic.info.queue.name"),true);
    }

    //创建交换机：在这里以DirectExchange为例，在后面章节中我们将继续详细介绍这种消息模型
    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange(env.getProperty("mq.basic.info.exchange.name"),true,false);
    }

    //创建绑定
    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with(env.getProperty("mq.basic.info.routing.key.name"));
    }


    /**创建简单消息模型-对象类型：队列、交换机和路由 **/

    //创建队列
    @Bean(name = "objectQueue")
    public Queue objectQueue(){
        return new Queue(env.getProperty("mq.object.info.queue.name"),true);
    }

    //创建交换机：在这里以DirectExchange为例，在后面章节中我们将继续详细介绍这种消息模型
    @Bean
    public DirectExchange objectExchange(){
        return new DirectExchange(env.getProperty("mq.object.info.exchange.name"),true,false);
    }

    //创建绑定
    @Bean
    public Binding objectBinding(){
        return BindingBuilder.bind(objectQueue()).to(objectExchange()).with(env.getProperty("mq.object.info.routing.key.name"));
    }



    /**创建消息模型-fanoutExchange **/

    //创建队列1
    @Bean(name = "fanoutQueueOne")
    public Queue fanoutQueueOne(){
        return new Queue(env.getProperty("mq.fanout.queue.one.name"),true);
    }

    //创建队列2
    @Bean(name = "fanoutQueueTwo")
    public Queue fanoutQueueTwo(){
        return new Queue(env.getProperty("mq.fanout.queue.two.name"),true);
    }

    //创建交换机-fanoutExchange
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(env.getProperty("mq.fanout.exchange.name"),true,false);
    }

    //创建绑定1
    @Bean
    public Binding fanoutBindingOne(){
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }

    //创建绑定2
    @Bean
    public Binding fanoutBindingTwo(){
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }


    /**创建消息模型-directExchange **/

    //创建交换机-directExchange
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(env.getProperty("mq.direct.exchange.name"),true,false);
    }

    //创建队列1
    @Bean(name = "directQueueOne")
    public Queue directQueueOne(){
        return new Queue(env.getProperty("mq.direct.queue.one.name"),true);
    }

    //创建队列2
    @Bean(name = "directQueueTwo")
    public Queue directQueueTwo(){
        return new Queue(env.getProperty("mq.direct.queue.two.name"),true);
    }

    //创建绑定1
    @Bean
    public Binding directBindingOne(){
        return BindingBuilder.bind(directQueueOne()).to(directExchange()).with(env.getProperty("mq.direct.routing.key.one.name"));
    }

    //创建绑定2
    @Bean
    public Binding directBindingTwo(){
        return BindingBuilder.bind(directQueueTwo()).to(directExchange()).with(env.getProperty("mq.direct.routing.key.two.name"));
    }


    /**创建消息模型-topicExchange **/

    //创建交换机-topicExchange
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(env.getProperty("mq.topic.exchange.name"),true,false);
    }

    //创建队列1
    @Bean(name = "topicQueueOne")
    public Queue topicQueueOne(){
        return new Queue(env.getProperty("mq.topic.queue.one.name"),true);
    }

    //创建队列2
    @Bean(name = "topicQueueTwo")
    public Queue topicQueueTwo(){
        return new Queue(env.getProperty("mq.topic.queue.two.name"),true);
    }

    //创建绑定1
    @Bean
    public Binding topicBindingOne(){
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(env.getProperty("mq.topic.routing.key.one.name"));
    }

    //创建绑定2
    @Bean
    public Binding topicBindingTwo(){
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(env.getProperty("mq.topic.routing.key.two.name"));
    }





    /**创建自动确认消费消息模型 **/

    /**
     * 单一消费者-确认模式为AUTO
     * @return
     */
    @Bean(name = "singleListenerContainerAuto")
    public SimpleRabbitListenerContainerFactory listenerContainerAuto(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        //设置确认消费模式为自动确认消费-AUTO
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    //创建队列
    @Bean(name = "autoQueue")
    public Queue autoQueue(){
        return new Queue(env.getProperty("mq.auto.knowledge.queue.name"),true);
    }

    //创建交换机
    @Bean
    public DirectExchange autoExchange(){
        return new DirectExchange(env.getProperty("mq.auto.knowledge.exchange.name"),true,false);
    }

    //创建绑定
    @Bean
    public Binding autoBinding(){
        return BindingBuilder.bind(autoQueue()).to(autoExchange()).with(env.getProperty("mq.auto.knowledge.routing.key.name"));
    }


    /**
     * 单一消费者-确认模式为MANUAL
     * @return
     */

    //创建队列
    @Bean(name = "manualQueue")
    public Queue manualQueue(){
        return new Queue(env.getProperty("mq.manual.knowledge.queue.name"),true);
    }
    //创建交换机
    @Bean
    public TopicExchange manualExchange(){
        return new TopicExchange(env.getProperty("mq.manual.knowledge.exchange.name"),true,false);
    }
    //创建绑定
    @Bean
    public Binding manualBinding(){
        return BindingBuilder.bind(manualQueue()).to(manualExchange()).with(env.getProperty("mq.manual.knowledge.routing.key.name"));
    }
    //定义手动确认消费模式对应的消费者实例
    @Autowired
    private KnowledgeManualConsumer knowledgeManualConsumer;

    /**
     * 创建单一消费者-确认模式为MANUAL-并指定监听的队列和消费者
     * @param manualQueue
     * @return
     */
    @Bean(name = "simpleContainerManual")
    public SimpleMessageListenerContainer simpleContainer(@Qualifier("manualQueue") Queue manualQueue){
        SimpleMessageListenerContainer container=new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());

        //TODO：并发配置
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setPrefetchCount(1);

        //TODO：消息确认模式-采用人为手动确认消费机制
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueues(manualQueue);
        container.setMessageListener(knowledgeManualConsumer);

        return container;
    }


    /**用户登录成功写日志消息模型创建**/

    //创建队列
    @Bean(name = "loginQueue")
    public Queue loginQueue(){
        return new Queue(env.getProperty("mq.login.queue.name"),true);
    }

    //创建交换机
    @Bean
    public TopicExchange loginExchange(){
        return new TopicExchange(env.getProperty("mq.login.exchange.name"),true,false);
    }

    //创建绑定
    @Bean
    public Binding loginBinding(){
        return BindingBuilder.bind(loginQueue()).to(loginExchange()).with(env.getProperty("mq.login.routing.key.name"));
    }


    /**死信队列消息模型构建**/

    //创建死信队列
    @Bean
    public Queue basicDeadQueue() {
        Map<String, Object> args = new HashMap();
        args.put("x-dead-letter-exchange", env.getProperty("mq.dead.exchange.name"));
        args.put("x-dead-letter-routing-key", env.getProperty("mq.dead.routing.key.name"));

        //设定TTL，单位为ms，在这里指的是10s
        args.put("x-message-ttl", 10000);
        return new Queue(env.getProperty("mq.dead.queue.name"), true, false, false, args);
    }

    //创建“基本消息模型”的基本交换机 - 面向生产者
    @Bean
    public TopicExchange basicProducerExchange() {
        return new TopicExchange(env.getProperty("mq.producer.basic.exchange.name"), true, false);
    }

    //创建“基本消息模型”的基本绑定-基本交换机+基本路由 - 面向生产者
    @Bean
    public Binding basicProducerBinding() {
        return BindingBuilder.bind(basicDeadQueue()).to(basicProducerExchange()).with(env.getProperty("mq.producer.basic.routing.key.name"));
    }

    //创建真正队列 - 面向消费者
    @Bean
    public Queue realConsumerQueue() {
        return new Queue(env.getProperty("mq.consumer.real.queue.name"), true);
    }

    //创建死信交换机
    @Bean
    public TopicExchange basicDeadExchange() {
        return new TopicExchange(env.getProperty("mq.dead.exchange.name"), true, false);
    }

    //创建死信路由及其绑定
    @Bean
    public Binding basicDeadBinding() {
        return BindingBuilder.bind(realConsumerQueue()).to(basicDeadExchange()).with(env.getProperty("mq.dead.routing.key.name"));
    }




    /**用户下单支付超时-RabbitMQ死信队列消息模型构建**/

    //创建死信队列
    @Bean
    public Queue orderDeadQueue() {
        Map<String, Object> args = new HashMap();
        args.put("x-dead-letter-exchange", env.getProperty("mq.order.dead.exchange.name"));
        args.put("x-dead-letter-routing-key", env.getProperty("mq.order.dead.routing.key.name"));

        //设定TTL，单位为ms，在这里为了测试方便，设置为10s，当然实际业务场景可能为1h或者更长
        args.put("x-message-ttl", 10000);
        return new Queue(env.getProperty("mq.order.dead.queue.name"), true, false, false, args);
    }

    //创建“基本消息模型”的基本交换机 - 面向生产者
    @Bean
    public TopicExchange orderProducerExchange() {
        return new TopicExchange(env.getProperty("mq.producer.order.exchange.name"), true, false);
    }

    //创建“基本消息模型”的基本绑定-基本交换机+基本路由 - 面向生产者
    @Bean
    public Binding orderProducerBinding() {
        return BindingBuilder.bind(orderDeadQueue()).to(orderProducerExchange()).with(env.getProperty("mq.producer.order.routing.key.name"));
    }

    //创建真正队列 - 面向消费者
    @Bean
    public Queue realOrderConsumerQueue() {
        return new Queue(env.getProperty("mq.consumer.order.real.queue.name"), true);
    }

    //创建死信交换机
    @Bean
    public TopicExchange basicOrderDeadExchange() {
        return new TopicExchange(env.getProperty("mq.order.dead.exchange.name"), true, false);
    }

    //创建死信路由及其绑定
    @Bean
    public Binding basicOrderDeadBinding() {
        return BindingBuilder.bind(realOrderConsumerQueue()).to(basicOrderDeadExchange()).with(env.getProperty("mq.order.dead.routing.key.name"));
    }


    /**Redisson篇章-RabbitMQ死信队列的缺陷**/

    //创建死信队列-由死信交换机+死信路由组成
    @Bean
    public Queue redissonBasicDeadQueue(){
        Map<String,Object> argsMap=new HashMap<>();
        argsMap.put("x-dead-letter-exchange", env.getProperty("mq.redisson.dead.exchange.name"));
        argsMap.put("x-dead-letter-routing-key", env.getProperty("mq.redisson.dead.routing.key.name"));
        return new Queue(env.getProperty("mq.redisson.dead.queue.name"),true,false,false,argsMap);
    }

    //创建基本交换机
    @Bean
    public TopicExchange redissonBasicExchange() {
        return new TopicExchange(env.getProperty("mq.redisson.dead.basic.exchange.name"), true, false);
    }

    //创建基本路由及其绑定-绑定到死信队列
    @Bean
    public Binding redissonBasicBinding() {
        return BindingBuilder.bind(redissonBasicDeadQueue())
                .to(redissonBasicExchange()).with(env.getProperty("mq.redisson.dead.basic.routing.key.name"));
    }

    //创建死信交换机
    @Bean
    public TopicExchange redissonBasicDeadExchange() {
        return new TopicExchange(env.getProperty("mq.redisson.dead.exchange.name"), true, false);
    }

    //创建真正队列 - 面向消费者
    @Bean
    public Queue redissonBasicDeadRealQueue() {
        return new Queue(env.getProperty("mq.redisson.real.queue.name"), true);
    }

    //创建死信路由及其绑定-绑定到真正的队列
    @Bean
    public Binding redissonBasicDeadRealBinding() {
        return BindingBuilder.bind(redissonBasicDeadRealQueue())
                .to(redissonBasicDeadExchange()).with(env.getProperty("mq.redisson.dead.routing.key.name"));
    }
}


















