package com.ganjunhao.constant;

/**
 * 响应码
 **/
public enum ResultCode implements IResultCode {
    SUCCESS(200, "操作成功"),
    FAILURE(400, "操作失败"),
    ARGUMENT_VALID_ERROR(401, "请求参数不符合要求"),
    UN_LOGIN(402, "用户未登录或登录过期"),
    UN_AUTHORIZED(403, "无权限请求该资源"),
    NOT_FOUND(404, "请求路径或资源不存在"),
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方式"),
    MSG_NOT_READABLE(406, "请求参数类型不对，消息不可读"),
    MEDIA_TYPE_NOT_SUPPORTED(407, "不支持当前媒体类型"),
    ENCODING_NOT_SUPPORTED(408, "不支持当前编码类型"),
    RESUBMIT(409, "表单重复提交"),
    FEIGN_CLIENT_EXCEPTION(410, "微服务异常"),
    USER_ALREADY_LOGGED(411, "已在别处登录，是否强制登录？"),
    REQUEST_LIMIT(429, "平台限流降级"),
    INTERNAL_SERVER_ERROR(500, "服务器内部异常");

    final int code;
    final String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    ResultCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Boolean isSuccess(int code) {
        return SUCCESS.getCode() == code;
    }
}