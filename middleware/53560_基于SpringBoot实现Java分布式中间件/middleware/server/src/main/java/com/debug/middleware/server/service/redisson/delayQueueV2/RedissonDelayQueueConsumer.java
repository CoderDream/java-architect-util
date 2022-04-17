package com.debug.middleware.server.service.redisson.delayQueueV2;/**
 * Created by Administrator on 2019/5/2.
 */

import com.debug.middleware.server.dto.DeadDto;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Redisson延迟队列消息模型-消费者
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/2 17:11
 **/
@Component
@EnableScheduling
public class RedissonDelayQueueConsumer{
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(RedissonDelayQueueConsumer.class);
    //定义Redisson的客户端操作实例
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 监听消费真正队列中的消息
     * 每时每刻都在不断的监听执行
     * @throws Exception
     */
    @Scheduled(cron = "*/1 * * * * ?")
    public void consumeMsg() throws Exception {
        //定义延迟队列的名称
        final String delayQueueName="redissonDelayQueueV3";
        RBlockingQueue<DeadDto> rBlockingQueue=redissonClient.getBlockingQueue(delayQueueName);

        //从队列中弹出消息
        DeadDto msg=rBlockingQueue.take();
        if (msg!=null){
            log.info("Redisson延迟队列消息模型-消费者-监听消费真正队列中的消息：{} ",msg);

            //TODO:在这里执行相应的业务逻辑
        }
    }
}




































