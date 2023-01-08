package com.demo.goods.restapi.mqchannel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface InputSource {
    String ORDERSCHANNEL = "orderschannel";
    String REPLYCHANNEL = "replychannel";

    @Input("orderschannel")
    SubscribableChannel ordersInput();

    @Output("replychannel")
    MessageChannel replyOutput();
}
