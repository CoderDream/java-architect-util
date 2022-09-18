package com.company.microserviceuser.enums;

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
    TOKEN_EMPTY(-1, "缺少Token"),
    TOKEN_INVALID(-2, "Token过期"),
    USER_NAME_NOT_EXISTS(-3, "用户不存在"),
    USER_NAME_PASSWORD_ERROR(-4, "用户名或密码错误"),
    LOGIN(-5, "请先登录"),
    ;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String msg;

    ResponseCodeEnums(Integer code, String msg) {
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