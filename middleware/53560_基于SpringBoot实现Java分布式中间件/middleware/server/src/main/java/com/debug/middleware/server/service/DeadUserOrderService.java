package com.debug.middleware.server.service;/**
 * Created by Administrator on 2019/4/11.
 */

import com.debug.middleware.model.entity.MqOrder;
import com.debug.middleware.model.entity.UserOrder;
import com.debug.middleware.model.mapper.MqOrderMapper;
import com.debug.middleware.model.mapper.UserOrderMapper;
import com.debug.middleware.server.dto.UserOrderDto;
import com.debug.middleware.server.rabbitmq.publisher.DeadOrderPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户下单支付超时-处理服务
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/11 23:34
 **/
@Service
public class DeadUserOrderService {

    private static final Logger log= LoggerFactory.getLogger(DeadUserOrderService.class);

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Autowired
    private MqOrderMapper mqOrderMapper;

    @Autowired
    private DeadOrderPublisher deadOrderPublisher;


    /**
     * 用户下单 - 并将生成的下单记录Id压入死信队列中等待延迟处理
     * @param userOrderDto
     * @throws Exception
     */
    public void pushUserOrder(UserOrderDto userOrderDto) throws Exception{
        //创建用户下单实例
        UserOrder userOrder=new UserOrder();
        //复制userOrderDto对应的字段取值到新的实例对象userOrder中
        BeanUtils.copyProperties(userOrderDto,userOrder);
        //设置支付状态为已保存
        userOrder.setStatus(1);
        //设置下单时间
        userOrder.setCreateTime(new Date());
        //插入用户下单记录
        userOrderMapper.insertSelective(userOrder);
        log.info("用户成功下单，下单信息为：{}",userOrder);

        //生成用户下单记录Id
        Integer orderId=userOrder.getId();
        //将生成的用户下单记录Id压入死信队列中等待延迟处理
        deadOrderPublisher.sendMsg(orderId);
    }


    /**
     * 更新用户下单记录的状态
     * @param userOrder
     */
    public void updateUserOrderRecord(UserOrder userOrder){
        try {
            if (userOrder!=null){
                //更新失效用户下单记录
                userOrder.setIsActive(0);
                userOrder.setUpdateTime(new Date());
                userOrderMapper.updateByPrimaryKeySelective(userOrder);

                //记录"失效用户下单记录"的历史
                MqOrder mqOrder=new MqOrder();
                mqOrder.setBusinessTime(new Date());
                mqOrder.setMemo("更新失效当前用户下单记录Id,orderId="+userOrder.getId());
                mqOrder.setOrderId(userOrder.getId());
                mqOrderMapper.insertSelective(mqOrder);
            }
        }catch (Exception e){
            log.error("用户下单支付超时-处理服务-更新用户下单记录的状态发生异常：",e.fillInStackTrace());
        }
    }
}


























