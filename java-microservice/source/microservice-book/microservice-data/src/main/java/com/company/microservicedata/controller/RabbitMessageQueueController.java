package com.company.microservicedata.controller;

import com.company.microservicedata.dto.MessageQueueInputDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import com.company.microservicedata.service.*;

/**
 * RabbitMQ Test.
 * @author xindaqi
 * @since 2020-12-02
 */
@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/v1/rabbitmq")
@Api(tags = "RabbitMQ")
public class RabbitMessageQueueController {

    @Autowired 
    private IMessageQueueSendService messageQueueSendService; 

    @RequestMapping(value="/direct/string", method=RequestMethod.POST)
    @ApiOperation("Direct发送String")
    public String directSendString(@RequestBody String msg) {
        messageQueueSendService.directSendString(msg);
        return "save";
    }

    @RequestMapping(value="/direct/object", method=RequestMethod.POST)
    @ApiOperation("Direct发送Object")
    public String directSendObject(@RequestBody MessageQueueInputDTO msg) {
        messageQueueSendService.directSendObject(msg);
        return "save";
    }

    @RequestMapping(value="/topic/one", method=RequestMethod.POST)
    @ApiOperation("TopicExchange发送消息")
    public String topicOneSend(@RequestBody String msg) {
        messageQueueSendService.topicOneSend(msg);
        return "save";
    }

    @RequestMapping(value="/topic/two", method=RequestMethod.POST)
    @ApiOperation("TopicExchange发送消息")
    public String topicTwoSend(@RequestBody String msg) {
        messageQueueSendService.topicTwoSend(msg);
        return "save";
    }

    @RequestMapping(value="/topic/otm", method=RequestMethod.POST)
    @ApiOperation("TopicExchange发送消息")
    public String topicManySend(@RequestBody String msg) {
        messageQueueSendService.topicManySend(msg);
        return "save";
    }

    @RequestMapping(value="/fanout/mtm", method=RequestMethod.POST)
    @ApiOperation("FanoutExchange发送消息")
    public String fanoutSend(@RequestBody String msg) {
        messageQueueSendService.fanoutSend(msg);
        return "save";
    }
}