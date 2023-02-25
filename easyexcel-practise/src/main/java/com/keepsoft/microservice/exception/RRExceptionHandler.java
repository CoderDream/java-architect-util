package com.keepsoft.microservice.exception;

import com.keepsoft.microservice.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

/**
 * 异常处理器 自动拦截controller抛出的错误
 *
 * @author wangwenbing
 * @email wangwenbing@163.com
 * @date 2017年11月4日 上午11:07:35
 */
@RestControllerAdvice
@Slf4j
public class RRExceptionHandler {
    /*
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public Result handleRRException(RRException e) {
        Result r = Result.error(e.getCode(), e.getMsg());
        log.error(e.getMsg(), e);
        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(SQLException.class)
    public Result handleSqlException(SQLException e) {
        log.error(e.getMessage(), e);
        return Result.error("数据库操作失败");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result handleAuthException(AccessDeniedException e) {
        log.error(e.getMessage(), e);
        return Result.error(ErrorDesc.AUTH_ERROR.getCode(), ErrorDesc.AUTH_ERROR.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {

        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }


}
