package com.example.demo.utils;

/**
 * 结果状态码
 * Created by turing_ios on 2018/8/7.
 */
public enum ResultStatusCode {
    OK(0, "OK"),
    SYSTEM_ERR(30001, "System error"),
    USER_ERR(1001,"用户信息错误");

    private int errcode;
    private String errmsg;
    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    private ResultStatusCode(int Errode, String ErrMsg)
    {
        this.errcode = Errode;
        this.errmsg = ErrMsg;
    }
}
