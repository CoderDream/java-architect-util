package com.debug.middleware.server.controller.redisson;/**
 * Created by Administrator on 2019/5/2.
 */

import com.debug.middleware.api.enums.StatusCode;
import com.debug.middleware.api.response.BaseResponse;
import com.debug.middleware.server.dto.DeadDto;
import com.debug.middleware.server.service.redisson.delayQueue.MqDelayQueuePublisher;
import com.debug.middleware.server.service.redisson.delayQueueV2.RedissonDelayQueuePublisher;
import com.debug.middleware.server.service.redisson.queue.QueuePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redisson的队列Controller
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/2 11:01
 **/
@RestController
public class QueueController {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(QueueController.class);
    //定义发送请求的前缀url
    private static final String prefix="queue";
    //定义发送日志的队列生产者实例
    @Autowired
    private QueuePublisher queuePublisher;

    /**
     * 发送消息
     * @param msg
     */
    @RequestMapping(value = prefix+"/basic/msg/send",method = RequestMethod.GET)
    public BaseResponse sendBasicMsg(@RequestParam String msg){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            queuePublisher.sendBasicMsg(msg);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    @Autowired
    private MqDelayQueuePublisher mqDelayQueuePublisher;

    /**
     * 发送mq延迟消息
     * @return
     */
    @RequestMapping(value = prefix+"/basic/msg/delay/send",method = RequestMethod.GET)
    public BaseResponse sendMqDelayMsg(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            DeadDto msgA=new DeadDto(1,"A");
            final Long ttlA=10000L;
            DeadDto msgB=new DeadDto(2,"B");
            final Long ttlB=2000L;
            DeadDto msgC=new DeadDto(3,"C");
            final Long ttlC=4000L;

            mqDelayQueuePublisher.sendDelayMsg(msgA,ttlA);
            mqDelayQueuePublisher.sendDelayMsg(msgB,ttlB);
            mqDelayQueuePublisher.sendDelayMsg(msgC,ttlC);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    @Autowired
    private RedissonDelayQueuePublisher redissonDelayQueuePublisher;

    /**
     * 发送redisson延迟消息
     * @return
     */
    @RequestMapping(value = prefix+"/redisson/msg/delay/send",method = RequestMethod.GET)
    public BaseResponse sendRedissonDelayMsg(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            DeadDto msgA=new DeadDto(1,"A");
            final Long ttlA=8000L;
            DeadDto msgB=new DeadDto(2,"B");
            final Long ttlB=2000L;
            DeadDto msgC=new DeadDto(3,"C");
            final Long ttlC=4000L;

            redissonDelayQueuePublisher.sendDelayMsg(msgA,ttlA);
            redissonDelayQueuePublisher.sendDelayMsg(msgB,ttlB);
            redissonDelayQueuePublisher.sendDelayMsg(msgC,ttlC);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}
























