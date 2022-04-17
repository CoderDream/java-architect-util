package com.debug.middleware.server.rabbitmq.consumer;/**
 * Created by Administrator on 2019/4/7.
 */

import com.debug.middleware.server.dto.UserLoginDto;
import com.debug.middleware.server.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 系统日志记录-消费者
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/7 19:18
 **/
@Component
public class LogConsumer {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(LogConsumer.class);
    //定义系统日志服务实例
    @Autowired
    private SysLogService sysLogService;
    /**
     * 监听消费并处理用户登录成功后的消息
     * @param loginDto
     */
    @RabbitListener(queues = "${mq.login.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMsg(@Payload UserLoginDto loginDto){
        try {
            log.info("系统日志记录-消费者-监听消费用户登录成功后的消息-内容：{}",loginDto);
            sysLogService.recordLog(loginDto);
        }catch (Exception e){
            log.error("系统日志记录-消费者-监听消费用户登录成功后的消息-发生异常：{} ",loginDto,e.fillInStackTrace());
        }
    }
}





























