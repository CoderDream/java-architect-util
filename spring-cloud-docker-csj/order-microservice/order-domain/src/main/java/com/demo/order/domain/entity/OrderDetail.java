package com.demo.order.domain.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_orderdetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long goodsid;
    private String goodsname;
    private String photo;
    private Integer nums;
    private float price;
    private double money;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created", columnDefinition = "timestamp default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public OrderDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
