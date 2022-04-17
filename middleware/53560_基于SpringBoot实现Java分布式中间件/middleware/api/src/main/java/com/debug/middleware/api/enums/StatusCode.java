package com.debug.middleware.api.enums;

/**
 * 通用状态码类
 */
public enum StatusCode {
    //以下是暂时设定的几种状态码类
    Success(0,"成功"),
    Fail(-1,"失败"),
    InvalidParams(201,"非法的参数!"),
    InvalidGrantType(202,"非法的授权类型");

    //状态码
    private Integer code;
    //描述信息
    private String msg;
    //重载的构造方法
    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
