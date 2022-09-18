package com.company.microservicedata.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息队列入参
 * @author xindaqi
 * @since 2020-12-06
 */
@Data
public class MessageQueueInputDTO implements Serializable {
    private static final long serialVersionUID = -202388015596677578L;

    private String username;

    private String phoneNumber;

    private String orderId;
}
