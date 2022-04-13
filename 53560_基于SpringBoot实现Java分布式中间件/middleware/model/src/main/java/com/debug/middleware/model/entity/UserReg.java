package com.debug.middleware.model.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 用户注册实体信息
 */
@Data
@ToString
public class UserReg {
    private Integer id; //用户id
    private String userName; //用户名
    private String password; //密码
    private Date createTime; //创建时间
}