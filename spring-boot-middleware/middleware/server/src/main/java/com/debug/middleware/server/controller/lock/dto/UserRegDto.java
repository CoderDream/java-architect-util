package com.debug.middleware.server.controller.lock.dto;/**
 * Created by Administrator on 2019/4/20.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户注册请求接收的信息封装的实体
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/20 20:24
 **/
@Data
@ToString
public class UserRegDto implements Serializable{
    private String userName; //用户名
    private String password; //密码
}