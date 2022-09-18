package com.company.microserviceuser.vo.common;

import com.company.microserviceuser.enums.*;

import java.io.Serializable;

/**
 * Uniform response. 
 * @author xindaqi
 * @since 2020-10-26
 */

public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = -9015277734735484348L;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String msg;

    /**
     * 数据总数量
     */
    private Long total;

    /**
     * 数据实体
     */
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

    /**
     * 成功返回code和msg
     *
     * @return
     */
    public ResponseVO<T> ok() {
        return new ResponseVO<>(ResponseCodeEnums.SUCCESS.getCode(), ResponseCodeEnums.SUCCESS.getMsg());
    }

    /**
     * 成功返回code，msg和data
     *
     * @param data
     * @return
     */
    public ResponseVO<T> ok(T data) {
        return new ResponseVO<>(ResponseCodeEnums.SUCCESS.getCode(), ResponseCodeEnums.SUCCESS.getMsg(), data);
    }

    /**
     * 成功返回：code，msg，data和total
     * @param data 数据实体
     * @param total 数据量
     * @return
     */
    public ResponseVO<T> ok(T data, Long total) {
        return new ResponseVO<>(ResponseCodeEnums.SUCCESS.getCode(), ResponseCodeEnums.SUCCESS.getMsg(), data, total);
    }

    /**
     * 失败返回：code和msg
     * @return
     */
    public ResponseVO<T> fail() {
        return new ResponseVO<>(ResponseCodeEnums.FAIL.getCode(), ResponseCodeEnums.FAIL.getMsg());
    }

    /**
     * 失败返回：code和msg
     * @param code 编码
     * @param msg 描述信息
     * @return
     */
    public ResponseVO<T> fail(Integer code, String msg) {
        return new ResponseVO<>(code, msg);

    }

    /**
     * 数据无效返回：code和msg
     * @return
     */
    public ResponseVO<T> invalid() {
        return new ResponseVO<>(ResponseCodeEnums.INVALID.getCode(), ResponseCodeEnums.FAIL.getMsg());
    }

    /**
     * 数据无效返回：code和msg
     * @param msg
     * @return
     */
    public ResponseVO<T> invalid(String msg) {
        return new ResponseVO<>(ResponseCodeEnums.INVALID.getCode(), msg);
    }

    /**
     * 数据空返回：code和msg
     * @return
     */
    public ResponseVO<T> empty() {
        return new ResponseVO<>(ResponseCodeEnums.EMPTY.getCode(), ResponseCodeEnums.EMPTY.getMsg());
    }

    /**
     * 数据空返回：code和msg
     * @param msg 描述信息
     * @return
     */
    public ResponseVO<T> empty(String msg) {
        return new ResponseVO<>(ResponseCodeEnums.EMPTY.getCode(), msg);
    }

    /**
     * 异常返回：code和msg
     * @param code 编码
     * @return
     */
    public ResponseVO<T> exception(Integer code) {
        switch (code) {
            case 4003:
                return new ResponseVO<>(ResponseCodeEnums.ARITHMETICEXCEPTION.getCode(), ResponseCodeEnums.ARITHMETICEXCEPTION.getMsg());
            case 4004:
                return new ResponseVO<>(ResponseCodeEnums.NULLPOINTEREXCEPTION.getCode(), ResponseCodeEnums.NULLPOINTEREXCEPTION.getMsg());
            default:
                return new ResponseVO<>(ResponseCodeEnums.INVALID.getCode(), ResponseCodeEnums.INVALID.getMsg());
        }
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


    @Override
    public String toString() {
        return "ResponseVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}