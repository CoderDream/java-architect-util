package com.company.microservicedata.service;

import org.springframework.amqp.core.Message;
import com.rabbitmq.client.Channel;
import java.io.IOException;

/**
 * 接收消息
 * @author xindaqi
 * @since 2020-12-03
 */
public interface IMessageQueueReceiveService {

    /**
     * 接收String消息
     * @param msg
     * @param channel
     * @throws IOException
     * @return
     * @author xindaqi
     * @since 2020-12-03
     */
    void directReceiveString(Message msg, Channel channel) throws IOException;

    /**
     * 接收Object消息
     * @param msg
     * @param channel
     * @throws IOException
     * @return
     * @author xindaqi
     * @since 2020-12-03
     */
    void directReceiveObject(Message msg, Channel channel) throws IOException;


    /**
     * 接收消息:topic一对一
     * @param msg
     * @param channel
     * @throws IOException
     * @return
     * @author xindaqi
     * @since 2020-12-03
     */
    void topicOneReceive(Message msg, Channel channel) throws IOException;

    /**
     * 接收消息:topic一对一
     * @param msg
     * @param channel
     * @throws IOException
     * @return
     * @author xindaqi
     * @since 2020-12-03
     */
    String topicTwoReceive(Message msg, Channel channel) throws IOException;


    /**
     * 接收消息:topic一对多
     * @param msg
     * @param channel
     * @throws IOException
     * @return
     * @author xindaqi
     * @since 2020-12-03
     */
    void topicManyReceive(Message msg, Channel channel) throws IOException;

    /**
     * 接收消息:fanout queue one
     * @param msg
     * @param channel
     * @throws IOException
     * @return
     * @author xindaqi
     * @since 2020-12-07
     */
    void fanoutOneReceive(Message msg, Channel channel) throws IOException;

    /**
     * 接收消息:fanout queue two
     * @param msg
     * @param channel
     * @throws IOException
     * @return
     * @author xindaqi
     * @since 2020-12-07
     */
    void fanoutTwoReceive(Message msg, Channel channel) throws IOException;
}