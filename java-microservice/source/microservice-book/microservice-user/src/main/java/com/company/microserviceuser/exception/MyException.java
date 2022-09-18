package com.company.microserviceuser.exception;

import static com.company.microserviceuser.constant.CodeConstant.ERROR_CODE;

/**
 * 自定义异常
 * @author xindaqi
 * @since 2020-12-25
 */

public class MyException extends Exception {

    /**
     * 编码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    public MyException() {}

    public MyException(String message) {
        super(message);
        this.message = message;
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public MyException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public static MyException paramException(String message) {
        return new MyException(ERROR_CODE, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
