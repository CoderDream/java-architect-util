package com.debug.middleware.model.entity;

import java.util.Date;

//RabbitMQ死信队列更新失效订单的状态实体
public class MqOrder {
    private Integer id; //主键id
    private Integer orderId; //下单记录id
    private Date businessTime; //失效下单记录状态的业务时间
    private String memo; //备注信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(Date businessTime) {
        this.businessTime = businessTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}