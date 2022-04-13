package com.debug.middleware.server.service.redisson.queue;/**
 * Created by Administrator on 2019/5/2.
 */

import org.assertj.core.util.Strings;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**队列的消费者
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/2 10:10
 **/
@Component
public class QueueConsumer implements ApplicationRunner,Ordered{
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(QueueConsumer.class);
    //定义Redisson的操作客户端实例
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 在项目运行启动成功之后执行该run方法
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //定义基本队列的名称
        final String queueName="redissonBasicQueue";
        //获取队列的实例
        RQueue<String> rQueue=redissonClient.getQueue(queueName);
        while (true){
            //从队列中弹出消息
            String msg=rQueue.poll();
            if (!Strings.isNullOrEmpty(msg)){
                log.info("队列的消费者-监听消费消息：{} ",msg);

                //TODO:在这里执行相应的业务逻辑
            }
        }
    }

    /*
    表示QueueConsumer将会在项目启动之后而跟随启动
     */
    @Override
    public int getOrder() {
        return -1;
    }
}


































