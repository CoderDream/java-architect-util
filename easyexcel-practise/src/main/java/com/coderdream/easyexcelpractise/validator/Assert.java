package com.coderdream.easyexcelpractise.validator;

import cn.hutool.core.util.StrUtil;
import com.keepsoft.microservice.exception.RRException;

/**
 * 数据校验
 *
 * @author wangwenbing
 * @email wangwenbing@163.com
 * @date 2017年11月4日 上午11:07:35
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StrUtil.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isBlank(String str, String message, Integer code) {
        if (StrUtil.isBlank(str)) {
            throw new RRException(message, code);
        }
    }

    public static void isBlank(Object object, String message, Integer code) {
        if (object == null || StrUtil.isBlank(String.valueOf(object))) {
            throw new RRException(message, code);
        }
    }

    public static void isNull(Object object, String message, Integer code) {
        if (object == null || StrUtil.isBlank(String.valueOf(object))) {
            throw new RRException(message, code);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
