package com.keepsoft.microservice.exception;

import cn.hutool.core.util.NumberUtil;

/**
 * 自定义异常
 *
 * @author wangwenbing
 * @email wangwenbing@163.com
 * @date 2017年11月4日 上午11:07:35
 */
public class RRException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public RRException(String msg) {
        super(msg);
        if (NumberUtil.isNumber(msg)) {
            this.code = Integer.valueOf(msg);
        }
        this.msg = msg;
    }

    public RRException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RRException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RRException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public RRException(ErrorDesc error) {
        super(error.getMsg());
        this.msg = error.getMsg();
        this.code = error.getCode();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
