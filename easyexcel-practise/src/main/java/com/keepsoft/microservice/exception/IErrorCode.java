package com.keepsoft.microservice.exception;

/**
 * 封装API的错误码
 *
 * @author hedf
 * @date 2019/4/19
 */
public interface IErrorCode {
    int getCode();

    String getMessage();
}
