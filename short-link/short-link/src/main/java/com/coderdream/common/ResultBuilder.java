package com.coderdream.common;

import java.util.List;

/**
 * @author CoderDream
 * @date Created in 2022/4/11 23:18
 * @description 结果集构造器
 */
public class ResultBuilder {
    private static final String SUCCESS = "Success";
    private static final String FAILURE = "Failure";

    public static <T> Result<T> buildSuccess(T data) {
        return new Result<T>(true, SUCCESS, SUCCESS, data);
    }

    public static <T> Result<List<T>> buildSuccessList(List<T> data) {
        return new Result(true, SUCCESS, SUCCESS, data);
    }

    public static <T> Result<T> buildSuccess() {
        return buildSuccess(null);
    }

    public static <T> Result<T> buildFailure(String message) {
        return new Result(false, FAILURE, message, (Object) null);
    }

    public static <T> Result<T> buildFailure(String code, String message) {
        return new Result(false, code, message, (Object) null);
    }
}