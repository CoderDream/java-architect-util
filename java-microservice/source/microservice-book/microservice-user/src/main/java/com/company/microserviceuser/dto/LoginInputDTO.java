package com.company.microserviceuser.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import com.company.microserviceuser.annotation.Desensitization;

/**
 * 用户注册入参
 *
 * @author xindaqi
 * @since 2020-10-08
 */
@ApiModel(description = "用户注册")
public class LoginInputDTO implements Serializable {

    private static final long serialVersionUID = -1710037905741402306L;

    @ApiModelProperty(value = "用户名", position = 1)
    @NotNull
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", position = 2)
    @NotNull
    @NotBlank(message = "密码不能为空")
    @Desensitization
    private transient String password;

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    @Override
    public String toString() {
        return "LoginInputDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}