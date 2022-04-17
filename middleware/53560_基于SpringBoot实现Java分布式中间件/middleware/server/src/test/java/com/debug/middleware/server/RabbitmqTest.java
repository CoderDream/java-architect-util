package com.debug.middleware.server;/**
 * Created by Administrator on 2019/3/30.
 */

import com.debug.middleware.server.rabbitmq.entity.DeadInfo;
import com.debug.middleware.server.rabbitmq.entity.EventInfo;
import com.debug.middleware.server.rabbitmq.entity.KnowledgeInfo;
import com.debug.middleware.server.rabbitmq.entity.Person;
import com.debug.middleware.server.rabbitmq.publisher.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/30 23:40
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RabbitmqTest {

    private static final Logger log= LoggerFactory.getLogger(RabbitmqTest.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BasicPublisher basicPublisher;

    @Test
    public void test1() throws Exception{
        String msg="~~~~这是一串字符串消息~~~~";
        basicPublisher.sendMsg(msg);
    }

    @Test
    public void test2() throws Exception{
        Person p=new Person(1,"大圣","debug");
        basicPublisher.sendObjectMsg(p);
    }

    @Autowired
    private ModelPublisher modelPublisher;

    @Test
    public void test3() throws Exception{
        EventInfo info=new EventInfo(1,"增删改查模块","基于fanoutExchange的消息模型","这是基于fanoutExchange的消息模型");
        modelPublisher.sendMsgFanout(info);
    }

    @Test
    public void test4() throws Exception{
        EventInfo info=new EventInfo(1,"增删改查模块-1","基于directExchange消息模型-1","directExchange-1");
        modelPublisher.sendMsgDirectOne(info);

        info=new EventInfo(2,"增删改查模块-2","基于directExchange消息模型-2","directExchange-2");
        modelPublisher.sendMsgDirectTwo(info);
    }


    @Test
    public void test5() throws Exception{
        String msg="这是TopicExchange消息模型的消息";

        //此时相当于*，即java替代了*的位置;
        //当然由于#表示任意个单词，故而也将路由到#表示的路由和对应的队列中
        String routingKeyOne="local.middleware.mq.topic.routing.java.key";
        //此时相当于#：即 php.python 替代了#的位置
        String routingKeyTwo="local.middleware.mq.topic.routing.php.python.key";
        //此时相当于#：即0个单词
        String routingKeyThree="local.middleware.mq.topic.routing.key";

        //modelPublisher.sendMsgTopic(msg,routingKeyOne);
        //modelPublisher.sendMsgTopic(msg,routingKeyTwo);
        modelPublisher.sendMsgTopic(msg,routingKeyThree);
    }


    @Autowired
    private KnowledgePublisher knowledgePublisher;

    @Test
    public void test6() throws Exception{
        KnowledgeInfo info=new KnowledgeInfo();
        info.setId(10010);
        info.setCode("auto");
        info.setMode("基于AUTO的消息确认消费模式");

        knowledgePublisher.sendAutoMsg(info);
    }

    @Autowired
    private KnowledgeManualPublisher knowledgeManualPublisher;

    @Test
    public void test7() throws Exception{
        KnowledgeInfo info=new KnowledgeInfo();
        info.setId(10011);
        info.setCode("manual");
        info.setMode("基于MANUAL的消息确认消费模式");

        knowledgeManualPublisher.sendAutoMsg(info);
    }



    @Autowired
    private DeadPublisher deadPublisher;

    @Test
    public void test8() throws Exception{
        //定义实体对象1
        DeadInfo info=new DeadInfo(1,"~~~~我是第一则消息~~~");
        //发送实体对象1消息入死信队列
        deadPublisher.sendMsg(info);

        //定义实体对象2
        info=new DeadInfo(2,"~~~~我是第二则消息~~~");
        //发送实体对象2消息入死信队列
        deadPublisher.sendMsg(info);

        //等待30s再结束-目的是为了能看到消费者监听真正队列中的消息
        Thread.sleep(30000);
    }
}































