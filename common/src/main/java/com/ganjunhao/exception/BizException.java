package com.ganjunhao.exception;


import com.ganjunhao.constant.ResultCode;

/**
 * @author jackesy
 * @className: BizException
 * @description: 业务异常
 * @create 2020/9/9 18:12
 **/
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1355046108056594333L;

    private ResultCode code;

    public BizException(String message) {
        super(message);
        this.code = ResultCode.FAILURE;
    }

    public BizException(String message, Throwable e) {
        super(message, e);
        this.code = ResultCode.FAILURE;
    }

    public BizException(ResultCode code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(ResultCode code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public ResultCode getCode() {
        return code;
    }

}
