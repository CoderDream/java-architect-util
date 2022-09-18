package com.company.microserviceuser.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户详情
 * @author xindaqi
 * @since 2020-10-07
 */
@ApiModel(description = "用户分页信息")
public class UserDetailsVO {

    @ApiModelProperty(value = "用户ID", position = 1)
    private Integer id;

    @ApiModelProperty(value = "用户姓名", position = 2)
    private String username;

    @ApiModelProperty(value = "用户性别", position = 3)
    private String sex;

    @ApiModelProperty(value = "用户住址", position = 4)
    private String address;

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return id;
    }

    public void setUserame(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }

    public void setSex(String sex){
        this.sex = sex;
    }
    public String getSex(){
        return sex;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }

    @Override
    public String toString() {
        return "UserDetailsVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}