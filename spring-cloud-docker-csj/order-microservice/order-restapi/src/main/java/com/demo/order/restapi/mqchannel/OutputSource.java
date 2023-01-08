package com.demo.order.restapi.mqchannel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputSource {
    String ORDERSCHANNEL = "orderschannel";

    @Output("orderschannel")
    MessageChannel ordersOutput();
}
