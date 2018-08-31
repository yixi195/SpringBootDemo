package com.ysl.demo.bean;

/**
 * 权限令牌
 * Created by turing_ios on 2018/8/31.
 */
public class Auth {
    private String userId;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
