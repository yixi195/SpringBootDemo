package com.ysl.demo.security;

import java.lang.annotation.*;

/**
 * 注解方式校验 Header 登录信息
 * Created by turing_ios on 2018/8/31.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckLogin {
    /**
     * 是否已登录
     * @return
     */
    boolean isLogin() default true;
}
