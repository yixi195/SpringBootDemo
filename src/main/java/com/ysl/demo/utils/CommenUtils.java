package com.ysl.demo.utils;

import com.ysl.demo.bean.Auth;

import javax.servlet.http.HttpServletRequest;

/**
 * 常用工具类
 * Created by turing_ios on 2018/8/31.
 */
public class CommenUtils {
    private static class SingletonHolder{
        private static final CommenUtils instance = new CommenUtils();
    }

    public static CommenUtils getInstance(){
        return SingletonHolder.instance;
    }

    /**
     * HttpServletRequest转令牌
     * @param httpServletRequest
     * @return
     */
    public Auth hsrToAuth(HttpServletRequest httpServletRequest){
        Auth auth = new Auth();
        auth.setUserId(httpServletRequest.getHeader("userId"));
        auth.setToken(httpServletRequest.getHeader("token"));
        return auth;
    }

}
