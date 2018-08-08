package com.example.demo.utils;

/**
 * 统一结果返回
 * Created by turing_ios on 2018/8/7.
 */
public class ResultMsg {
    private int code; //错误码
    private String msg;
    private Object data;

    public ResultMsg(int code, String msg, Object data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public int getcode() {
        return code;
    }
    public void setcode(int code) {
        this.code = code;
    }
    public String getmsg() {
        return msg;
    }
    public void setmsg(String msg) {
        this.msg = msg;
    }
    public Object getdata() {
        return data;
    }
    public void setdata(Object data) {
        this.data = data;
    }
}
