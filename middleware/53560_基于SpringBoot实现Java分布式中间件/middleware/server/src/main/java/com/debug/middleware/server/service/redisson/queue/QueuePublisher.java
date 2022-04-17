package com.debug.middleware.server.service.redisson.queue;/**
 * Created by Administrator on 2019/5/2.
 */

import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 队列的生产者
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/2 10:10
 **/
@Component
public class QueuePublisher {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(QueuePublisher.class);
    //定义Redisson的操作客户端实例
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 发送基本的消息
     * @param msg 待发送的消息
     */
    public void sendBasicMsg(String msg){
        try {
            //定义基本队列的名称
            final String queueName="redissonBasicQueue";
            //获取队列的实例
            RQueue<String> rQueue=redissonClient.getQueue(queueName);
            //往队列中发送消息
            rQueue.add(msg);
            log.info("队列的生产者-发送基本的消息-消息发送成功：{} ",msg);
        }catch (Exception e){
            log.error("队列的生产者-发送基本的消息-发生异常：{} ",msg,e.fillInStackTrace());
        }
    }
}






























































