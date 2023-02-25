package com.keepsoft.microservice.exception;

/**
 * 错误枚举类
 *
 * @author wangwenbing
 * @date 2017年11月4日 上午11:07:35
 */
public enum ErrorDesc {
    SESSION_EMPTY(10000, "SESSION过期或不存在"),
    SYSTEM_ERR(10001, "系统出错，请稍后再试"),
    PARAM_EMPTY(10002, "参数不能为空"),
    CUST_CODE_ERR(800, "授权码错误"),
    AUTH_ERROR(401, "没有授权"),
    RESULT_EMPTY_ERROR(10003,"没有查询记录"),

    FILE_PATH_ISNULL(10004, "文件路径为空"),

    FILE_ISNULL(10005, "文件为空"),

    FILE_UPLOAD_FAILED(10006, "文件上传失败"),

    FILE_NOT_EXIST(10007, "文件不存在"),

    FILE_DOWNLOAD_FAILED(10008, "文件下载失败"),

    FILE_DELETE_FAILED(10009, "删除文件失败"),

    FILE_SERVER_CONNECTION_FAILED(10010, "文件服务器连接失败"),

    FILE_OUT_SIZE(10011, "文件超过大小"),

    FILE_TYPE_ERROR_IMAGE(10012, "图片类型错误"),

    FILE_TYPE_ERROR_DOC(10013, "文档类型错误"),

    FILE_TYPE_ERROR_VIDEO(10014, "音频类型错误"),

    FILE_TYPE_ERROR_COMPRESS(10015, "压缩文件类型错误"),

    FLOW_PROCESS_ID_EMPTY(10016, "流程Id为空"),

    FLOW_PROCESS_PARMA_EMPTY(10017, "流程参数为空"),

    FLOW_PROCESS_NOT_EXIST(10018, "流程不存在"),

    FLOW_PROCESS_START_ERROR(10019, "流程启动失败"),

    FLOW_PROCESS_DGRAM_ERROR(10020, "生成流程图失败");

    private Integer code;
    private String msg;

    ErrorDesc(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
