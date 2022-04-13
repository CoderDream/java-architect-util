package com.debug.middleware.server.service.redisson.delayQueue;/**
 * Created by Administrator on 2019/5/2.
 */

import com.debug.middleware.server.dto.DeadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ死信队列消息模型-消费者
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/2 17:11
 **/
@Component
public class MqDelayQueueConsumer {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(MqDelayQueueConsumer.class);
    //定义RabbitMQ发送消息的操作组件实例
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //定义读取环境变量的实例env
    @Autowired
    private Environment env;

    /**
     * 监听消费真正队列中的消息
     * @param deadDto
     */
    @RabbitListener(queues = "${mq.redisson.real.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMsg(@Payload DeadDto deadDto){
        try {
            log.info("RabbitMQ死信队列消息模型-消费者-监听消费真正队列中的消息：{}",deadDto);

            //TODO:在这里执行真正的业务逻辑

        }catch (Exception e){
            log.error("RabbitMQ死信队列消息模型-消费者-监听消费真正队列中的消息-发生异常：{}",deadDto,e.fillInStackTrace());
        }
    }
}




































