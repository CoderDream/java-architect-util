package com.demo.order.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="order_no",length = 64)
    private String orderNo;
    private Long userid;
    private Long merchantid;
    private double amount;
    //0：未付款，1：已付款，2：已发货，3：已收货，4：已评价，-1：已撤销，-2：已退款
    @Column(name = "status", columnDefinition = "tinyint default 0")
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created", columnDefinition = "timestamp default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private String operator;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modify;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "order_detail",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "detail_id")})
    @JsonBackReference
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Order() {
    }

    public void addOrderDetails(OrderDetail orderDetail){
        orderDetails.add(orderDetail);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getModify() {
        return modify;
    }

    public void setModify(Date modify) {
        this.modify = modify;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
