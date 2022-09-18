package com.company.microserviceuser.annotation.impl;

/**
 * 自动try...catch...实现
 * @author xindaqi
 * @since 2020-12-26
 */
public class CatchThrowsImpl {
    public static RuntimeException catchThrow(Throwable t) {
        if (t == null) {
            throw new NullPointerException("t");
        }
        return CatchThrowsImpl.<RuntimeException>catchThrow0(t);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> T catchThrow0(Throwable t) throws T {
        throw (T)t;
    }
}
