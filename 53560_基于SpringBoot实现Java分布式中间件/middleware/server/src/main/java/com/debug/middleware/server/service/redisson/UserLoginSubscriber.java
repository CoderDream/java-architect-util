package com.debug.middleware.server.service.redisson;/**
 * Created by Administrator on 2019/4/28.
 */

import com.debug.middleware.server.dto.UserLoginDto;
import com.debug.middleware.server.service.SysLogService;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 记录用户登录成功后的轨迹-消费者
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/28 22:08
 **/
@Component
public class UserLoginSubscriber implements ApplicationRunner,Ordered{
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(UserLoginSubscriber.class);
    //构造基于发布-订阅式主题的Key
    private static final String topicKey="redissonUserLoginTopicKey";
    //构造Redisson客户端操作实例
    @Autowired
    private RedissonClient redissonClient;
    //定义系统日志服务实例
    @Autowired
    private SysLogService sysLogService;

    /**
     * 在这个方法里实现“不断地监听该主题中消息的动态” - 即间接地实现自动监听消费
     * @param arguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments arguments) throws Exception {
        try {
            RTopic rTopic=redissonClient.getTopic(topicKey);
            rTopic.addListener(UserLoginDto.class, new MessageListener<UserLoginDto>() {
                @Override
                public void onMessage(CharSequence charSequence, UserLoginDto dto) {
                    log.info("记录用户登录成功后的轨迹-消费者-监听消费到消息：{} ",dto);

                    //判断消息是否为null
                    if (dto!=null){
                        //如果消息不为null,则将消息记录入数据库中
                        sysLogService.recordLog(dto);
                    }
                }
            });
        }catch (Exception e){
            log.error("记录用户登录成功后的轨迹-消费者-发生异常：",e.fillInStackTrace());
        }
    }

    /**
     * 设置项目启动时也跟着启动
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}


















