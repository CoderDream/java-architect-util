package com.debug.middleware.server.service.redisson;/**
 * Created by Administrator on 2019/4/28.
 */

import com.debug.middleware.server.dto.UserLoginDto;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 记录用户登录成功后的轨迹-生产者
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/28 22:07
 **/
@Component
public class UserLoginPublisher {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(UserLoginPublisher.class);
    //构造基于发布-订阅式主题的Key
    private static final String topicKey="redissonUserLoginTopicKey";
    //构造Redisson客户端操作实例
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 异步发送消息
     * @param dto
     */
    public void sendMsg(UserLoginDto dto){
        try {
            //判断消息对象是否为null
            if (dto!=null){
                log.info("记录用户登录成功后的轨迹-生产者-发送消息：{} ",dto);

                //创建主题
                RTopic rTopic=redissonClient.getTopic(topicKey);
                //发布消息
                rTopic.publishAsync(dto);
            }
        }catch (Exception e){
            log.error("记录用户登录成功后的轨迹-生产者-发生异常：{}",dto,e.fillInStackTrace());
        }
    }
}


















