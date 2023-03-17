package com.ganjunhao.constant;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.io.Serializable;

/**
 * @author jackesy
 * @className: R
 * @description: 响应对象
 * @create 2020/9/9 14:56
 **/
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code = ResultCode.SUCCESS.getCode();

    /**
     * 业务状态码：针对特殊业务场景
     */
    private int biz_code = BizResultCode.DEFAULT.getCode();

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 服务器时间
     */
    private String server_time = DateUtil.now();

    public R() {
    }

    private R(ResultCode code, T data, String msg, BizResultCode biz_code) {
        this.code = code.getCode();
        this.biz_code = biz_code.getCode();
        this.data = data;
        this.msg = (StrUtil.isEmpty(msg) ? code.getMsg() : msg);
        this.success = (ResultCode.SUCCESS.getCode() == code.getCode());
        if (!this.success) {
            this.success = (ResultCode.INTERNAL_SERVER_ERROR.getCode() == code.getCode());
        }
    }

    public boolean isSuccess() {
        return ResultCode.isSuccess(this.code) || (ResultCode.SUCCESS.getCode() == this.code);
    }

    public boolean isFail() {
        return !isSuccess();
    }

    public static <T> R<T> ok() {
        return new R(ResultCode.SUCCESS, null, ResultCode.SUCCESS.getMsg(), BizResultCode.DEFAULT);
    }

    public static <T> R<T> ok(T data) {
        return new R(ResultCode.SUCCESS, data, ResultCode.SUCCESS.getMsg(), BizResultCode.DEFAULT);
    }

    public static <T> R<T> ok(ResultCode resultCode) {
        return new R(resultCode, null, resultCode.getMsg(), BizResultCode.DEFAULT);
    }

    public static <T> R<T> ok(ResultCode resultCode, T data) {
        return new R(resultCode, data, resultCode.getMsg(), BizResultCode.DEFAULT);
    }

    public static <T> R<T> ok(String msg) {
        return new R(ResultCode.SUCCESS, null, msg, BizResultCode.DEFAULT);
    }

    public static <T> R<T> ok(T data, String msg) {
        return new R(ResultCode.SUCCESS, data, msg, BizResultCode.DEFAULT);
    }

    public static <T> R<T> ok(T data, BizResultCode biz_code) {
        return new R(ResultCode.SUCCESS, data, null, biz_code);
    }

    public static <T> R<T> fail(String msg) {
        return new R(ResultCode.FAILURE, null, msg, BizResultCode.DEFAULT);
    }

    public static <T> R<T> fail(ResultCode code) {
        return new R(code, null, code.getMsg(), BizResultCode.DEFAULT);
    }

    public static <T> R<T> fail(ResultCode code, T date) {
        return new R(code, date, code.getMsg(), BizResultCode.DEFAULT);
    }

    public static <T> R<T> fail(ResultCode code, String msg) {
        return new R(code, null, msg, BizResultCode.DEFAULT);
    }

    public static <T> R<T> fail(ResultCode code, String msg, BizResultCode biz_code) {
        return new R(code, null, msg, biz_code);
    }

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public int getBiz_code() {
        return biz_code;
    }

    public void setBiz_code(int biz_code) {
        this.biz_code = biz_code;
    }

    public String getServer_time() {
        return server_time;
    }

    public void setServer_time(String server_time) {
        this.server_time = server_time;
    }
}
