package com.company.microserviceuser.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册出参
 *
 * @author xindaqi
 * @since 2020-10-11
 */
@Data
public class LoginOutputDTO implements Serializable {

    private static final long serialVersionUID = 4225187635878542621L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码,忽略password序列化
     */
    private transient String password;
}