package com.company.microservicedata.vo.common;

import com.company.microservicedata.enums.*;

/**
 * Uniform response. 
 * @author xindaqi
 * @since 2020-10-26
 */

public class ResponseVO<T> {

    private Integer code;

    private String msg;

    private Long total;

    private T data;

    public ResponseVO() {}

    public ResponseVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseVO(Integer code, String msg, T data, Long total) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.total = total;
    }

    public ResponseVO<T> ok() {
        return new ResponseVO<T>(ResponseCodeEnums.SUCCESS.getCode(), ResponseCodeEnums.SUCCESS.getMsg());
    }

    public ResponseVO<T> ok(T data) {
        return new ResponseVO<T>(ResponseCodeEnums.SUCCESS.getCode(), ResponseCodeEnums.SUCCESS.getMsg(), data);
    }

    public ResponseVO<T> ok(T data, Long total) {
        return new ResponseVO<T>(ResponseCodeEnums.SUCCESS.getCode(), ResponseCodeEnums.SUCCESS.getMsg(), data, total);
    }

    public ResponseVO<T> fail() {
        return new ResponseVO<T>(ResponseCodeEnums.FAIL.getCode(), ResponseCodeEnums.FAIL.getMsg());
    }

    public ResponseVO<T> invalid() {
        return new ResponseVO<>(ResponseCodeEnums.INVALID.getCode(), ResponseCodeEnums.FAIL.getMsg());
    }

    public ResponseVO<T> invalid(String msg) {
        return new ResponseVO<T>(ResponseCodeEnums.INVALID.getCode(), msg);
    }

    public ResponseVO<T> empty() {
        return new ResponseVO<T>(ResponseCodeEnums.EMPTY.getCode(), ResponseCodeEnums.EMPTY.getMsg());
    }

    public ResponseVO<T> exception(Integer code) {
        switch (code) {
            case 4003:
                return new ResponseVO<T>(ResponseCodeEnums.ARITHMETICEXCEPTION.getCode(), ResponseCodeEnums.ARITHMETICEXCEPTION.getMsg());
                
            case 4004:
                return new ResponseVO<T>(ResponseCodeEnums.NULLPOINTEREXCEPTION.getCode(), ResponseCodeEnums.NULLPOINTEREXCEPTION.getMsg());
            default:
                return new ResponseVO<T>(ResponseCodeEnums.INVALID.getCode(), ResponseCodeEnums.INVALID.getMsg());
        }
        // return new ResponseVO<T>(ResponseCodeEnums.INVALID.getCode(), ResponseCodeEnums.INVALID.getMsg());
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    public Long getTotal() {
        return total;
    }

    
}