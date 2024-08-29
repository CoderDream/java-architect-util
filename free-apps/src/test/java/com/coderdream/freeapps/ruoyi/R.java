package com.coderdream.freeapps.ruoyi;

import java.util.HashMap;

public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static R error(String msg) {
        R json = new R();
        json.put("msg", msg);
        json.put("code", 500);
        return json;
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static R fail(String msg) {
        R json = new R();
        json.put("msg", msg);
        json.put("code", 500);
        return json;
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static R ok(String msg) {
        R json = new R();
        json.put("msg", msg);
        json.put("code", 500);
        return json;
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static R ok(String code, String msg) {
        R json = new R();
        json.put("msg", msg);
        json.put("code", code);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static R success(String msg) {
        R json = new R();
        json.put("msg", msg);
        json.put("code", 0);
        return json;
    }
}
