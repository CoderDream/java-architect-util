package com.company.microservicedata.service;

import com.company.microservicedata.dto.MessageQueueInputDTO;

/**
 * 发送消息
 * @author xindaqi
 * @since 2020-12-03
 */
public interface IMessageQueueSendService {

    /**
     * 发送消息:Direct模式
     * @param params 字符串
     * @return
     * @author xindaqi
     * @since 2020-12-03
     */
    void directSendString(String params);

    /**
     * 发送消息:Direct模式
     * @param params 对象
     * @return
     * @author xindaqi
     * @since 2020-12-03
     */
    void directSendObject(MessageQueueInputDTO params);

    /**
     * 发送消息:Topic一对一模式
     * @param params 测试
     * @return
     * @author xindaqi
     * @since 2020-12-03
     */
    void topicOneSend(String params);

    /**
     * 发送消息:Topic一对一模式
     * @param params 测试
     * @return
     * @author xindaqi
     * @since 2020-12-03
     */
    void topicTwoSend(String params);

    /**
     * 发送消息:Topic一对多模式
     * @param params 测试
     * @return
     * @author xindaqi
     * @since 2020-12-03
     */
    void topicManySend(String params);

    /**
     * 发送消息: Fanout模式
     * @param params 消息
     * @return
     * @author xindaqi
     * @since 2020-12-07
     */
    void fanoutSend(String params);
    
}