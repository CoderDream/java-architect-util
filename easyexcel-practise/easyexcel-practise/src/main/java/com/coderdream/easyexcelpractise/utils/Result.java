package com.coderdream.easyexcelpractise.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 7611072148148193425L;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 信息
     */
    private String msg;

    private Long handlerTime;

    private Integer timezone;

    /**
     * 数据
     */
    private T data;

    public Result() {
        this.code = 200;
        this.msg = "success";
        this.handlerTime = System.currentTimeMillis();
        this.timezone = 8;
    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.code = code;
        r.msg = msg;
        return r;
    }

    public static Result ok(int code) {
        Result r = new Result();
        r.code = code;
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.msg = msg;
        return r;
    }


    public Result data(T data) {
        this.data = data;
        return this;
    }

    public static <T> Result<T> ok() {
        return new Result<>();
    }

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.setData(data);
        return r;
    }

    public static <T> Result<T> ok(String msg, T data) {
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
