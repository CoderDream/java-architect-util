package com.coderdream.middleware.api.response;


import com.coderdream.middleware.api.enums.StatusCode;

/**
 * @author: debug
 * @date: 2018/12/129
 */
public class BaseResponse<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String msg;

    /**
     * 响应数据-采用泛型表示可以接受通用的数据类型
     */
    private T data;

    /**
     * 重载的构造方法一
     * @param code 状态码
     * @param msg 描述信息
     */
    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     *
     * @param statusCode 状态码
     */
    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    /**
     *
     * @param code 状态码
     * @param msg 描述信息
     * @param data 响应数据
     */
    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
