package com.debug.middleware.server.service.redisson.delayQueueV2;/**
 * Created by Administrator on 2019/5/2.
 */

import com.debug.middleware.server.dto.DeadDto;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * Redisson延迟队列消息模型-生产者
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/2 17:10
 **/
@Component
public class RedissonDelayQueuePublisher {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(RedissonDelayQueuePublisher.class);
    //定义Redisson的客户端操作实例
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 发送消息入延迟队列
     * @param msg 消息
     * @param ttl 消息的存活时间-可以随意指定时间单位，在这里指毫秒
     */
    public void sendDelayMsg(final DeadDto msg, final Long ttl){
        try {
            //定义延迟队列的名称
            final String delayQueueName="redissonDelayQueueV3";
            //定义获取阻塞式队列的实例
            RBlockingQueue<DeadDto> rBlockingQueue=redissonClient.getBlockingQueue(delayQueueName);
            //定义获取延迟队列的实例
            RDelayedQueue<DeadDto> rDelayedQueue=redissonClient.getDelayedQueue(rBlockingQueue);
            //往延迟队列发送消息-设置的TTL，相当于延迟了“阻塞队列”中消息的接收
            rDelayedQueue.offer(msg,ttl,TimeUnit.MILLISECONDS);

            log.info("Redisson延迟队列消息模型-生产者-发送消息入延迟队列-消息：{}",msg);
        }catch (Exception e){
            log.error("Redisson延迟队列消息模型-生产者-发送消息入延迟队列-发生异常：{}",msg,e.fillInStackTrace());
        }
    }
}
























































