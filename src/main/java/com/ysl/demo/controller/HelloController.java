package com.ysl.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Hello
 * Created by turing_ios on 2018/8/6.
 */
@RestController
public class HelloController {

    @Value(value = "${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;
    @Value("${book.pinyin}")
    private String bookPinYin;

    /**
     * 第一个
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot11!";
    }


    /**
     * 测试properties配置
     * @return
     */
    @RequestMapping("/print")
    public String print(){
        return "作者:" + bookAuthor + " |书名:" + bookName + " |拼音名:" + bookPinYin;
    }

//    @RequestMapping("/print2")
//    public String print2(){
//        return "作者:" + bookBean.getAuthor() + " |书名:" + bookBean.getName() + " |价格:" + bookBean.getPrice();
//    }

}
