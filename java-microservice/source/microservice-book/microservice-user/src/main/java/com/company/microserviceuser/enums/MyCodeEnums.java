package com.company.microserviceuser.enums;

/**
 * 自定义枚举
 * 
 * @author xindaqi
 * @since 2020-01-01
 */

public enum MyCodeEnums {

    USERNAME_PASSWORD_ERROR(5000, "用户名或密码错误"),
    USERNAME_EMPTY(5001, "用户名不能为空"),
    USERNAME_INVALID(5002, "请输入用户名"),
    PASSWORD_EMPTY(5003, "密码不能为空"),
    PASSWORD_INVALID(5004, "请输入密码"),
    LOGIN_SUCCESSFUL(200, "登录成功"),
    ;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String msg;

    MyCodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    
}