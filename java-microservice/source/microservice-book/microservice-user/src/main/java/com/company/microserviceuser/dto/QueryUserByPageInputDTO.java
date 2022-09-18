package com.company.microserviceuser.dto;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息分页查询
 * @author xindaqi
 * @since 2020-11-29
 */

@Data
@ApiModel(description = "分页查询用户信息入参")
public class QueryUserByPageInputDTO implements Serializable {
    private static final long serialVersionUID = 4683113122303496296L;
    
    @ApiModelProperty(value = "用户ID", position = 1)
    private Long id;

    @ApiModelProperty(value = "用户姓名", position = 2)
    private String username;

    @ApiModelProperty(value = "用户性别", position = 3)
    private String sex;

    @ApiModelProperty(value = "用户住址", position = 4)
    private String address;

    @ApiModelProperty(value = "用户手机号", position = 5)
    private String phoneNumber;

    @ApiModelProperty(value = "当前页码", position = 6)
    private Integer pageNum;

    @ApiModelProperty(value = "当前页数据行数", position = 7)
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