package com.company.microservicedata.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户信息分页查询
 * @author xindaqi
 * @since 2020-11-29
 */

@Data
public class QueryUserByPageInputDTO implements Serializable {
    private static final long serialVersionUID = 4683113122303496296L;
    private Long id;

    private String username;

    private String sex;

    private String address;

    private String phoneNumber;

    private Integer pageNum;

    private Integer pageSize;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    
}