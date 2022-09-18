package com.company.microservicedata.enums;

/**
 * Uniform code response. 
 * @author xindaqi
 * @since 2020-10-26
 */

public enum ResponseCodeEnums {

    SUCCESS(200, "成功"),
    FAIL(4000, "失败"),
    EMPTY(4001, "空数据"),
    INVALID(4002, "数据无效"),
    UNAUTHENTATION(4003, "未授权"),
    ARITHMETICEXCEPTION(4004, "计算异常"),
    NULLPOINTEREXCEPTION(4005, "空指针异常"),
    ;

    private Integer code;

    private String msg;

    ResponseCodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
    
}