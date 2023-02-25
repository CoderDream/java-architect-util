package com.keepsoft.microservice.exception;

/**
 * 枚举了一些常用API操作码
 *
 * @author hedf
 */
public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),
    FAILED(-1, "操作失败"),
    VALIDATE_FAILED(300, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    FLOW_LIMIT(502,"您已被流控管制，请稍后再试"),
    DEGRADE_LIMIT(503,"您已被熔断降级，请稍后再试"),
    PARAM_FLOW_LIMIT(504,"您已被热点限流，请稍后再试"),
    AUTHORITY_LIMIT(505,"您已进入黑名单，无权访问，请联系管理员"),
    SYSTEM_BLOCK_LIMIT(506,"您已被系统规则限制，请稍后再试"),
    CONNECT_TIME_OUT(507,"请求服务连接超时"),
    NOTFOUND_APPID(508,"授权标识APPID不存在"),
    ILLEGAL_APPID(509,"授权标识APPID不合法"),
    INVALIDATION_APPID(510,"授权标识APPID已失效"),
    ILLEGAL_INTERFACE(511,"请求URL不合法"),
    ILLEGAL_IP(512,"客户端IP不允许访问"),
    ILLEGAL_GATEWAY_ROUTE_URI(513,"网关路由URI不合法");

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
