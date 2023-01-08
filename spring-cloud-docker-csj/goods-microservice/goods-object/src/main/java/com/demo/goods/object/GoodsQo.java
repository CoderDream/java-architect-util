package com.demo.goods.object;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class GoodsQo extends PageQo{
    private Long id;
    private Long merchantid;
    private Long sortsid;
    private Long subsid;
    private String name;
    private String contents;
    private String photo;
    private float price;
    private Integer buynum;
    private Integer reserve;
    private String operator;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    public GoodsQo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }

    public Long getSortsid() {
        return sortsid;
    }

    public void setSortsid(Long sortsid) {
        this.sortsid = sortsid;
    }

    public Long getSubsid() {
        return subsid;
    }

    public void setSubsid(Long subsid) {
        this.subsid = subsid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getBuynum() {
        return buynum;
    }

    public void setBuynum(Integer buynum) {
        this.buynum = buynum;
    }

    public Integer getReserve() {
        return reserve;
    }

    public void setReserve(Integer reserve) {
        this.reserve = reserve;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
