package com.coderdream.autogenvedio.spiderwx;

/**
 * 响应实体
 * @author sunnyzyq
 * @since 2019/04/23
 */
public class Resp<T> {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    int code = SUCCESS;
    String msg;
    T body;

    public Resp() {}

    public Resp(T t) {
        this.body = t;
    }

    public Resp(int code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public static <T> Resp<T> error() {
        return new Resp<>(ERROR, null, null);
    }

    public static <T> Resp<T> error(String msg) {
        return new Resp<>(ERROR, msg, null);
    }

    public static <T> Resp<T> error(String msg, T body) {
        return new Resp<>(ERROR, msg, body);
    }

    public static <T> Resp<T> success() {
        return new Resp<>(SUCCESS, null, null);
    }

    public static <T> Resp<T> success(String msg) {
        return new Resp<>(SUCCESS, msg, null);
    }

    public static <T> Resp<T> success(T body) {
        return new Resp<>(SUCCESS, "", body);
    }

    public static <T> Resp<T> success(String msg, T body) {
        return new Resp<>(SUCCESS, msg, body);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public boolean isError() {
        return code != SUCCESS;
    }

    public boolean isSuccess() {
        return code == SUCCESS;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("}");
        sb.append("code:").append(code).append(",");
        if (msg != null) {
            sb.append("msg:").append(msg).append(",");
        }
        if (body != null) {
            sb.append("body:").append(body.toString());
        }
        sb.append("}");
        return sb.toString();
    }
}
