package com.ganjunhao.constant;

/**
 * 响应码
 **/
public enum BizResultCode implements IResultCode {

    DEFAULT(10000, "");

    final int code;
    final String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    BizResultCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}