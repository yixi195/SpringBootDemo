package com.ysl.demo.controller;

import com.ysl.demo.utils.ResultMsg;
import com.ysl.demo.bean.User;
import com.ysl.demo.repository.UserRepository;
import com.ysl.demo.utils.ResultStatusCode;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 用户api
 * <p>
 * save,update ,delete 方法需要绑定事务. 使用@Transactional进行事务的绑定.
 * Created by turing_ios on 2018/8/7.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * 获取用户信息  GET\POST都可以
     *
     * @param userId
     * @return
     */
    @RequestMapping("/userInfo")
    public Object getUser(String userId, String token) {
        //根据 UserId和Token同时查询
        User userBean = userRepository.findByUserIdAndToken(userId, token);
        ResultMsg resultMsg;
        if (null == userBean) {
            resultMsg = new ResultMsg(ResultStatusCode.USER_ERR.getErrcode(), ResultStatusCode.USER_ERR.getErrmsg(), null);
        } else {
            resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userBean);
        }
        return resultMsg;
    }

    /**
     * 添加用户  POST 请求
     * 方法一: application/json
     *
     * @param userEntity
     * @return
     */
    @Modifying
    @PostMapping("/adduser")
    public Object addUser(@RequestBody User userEntity) {
        System.out.println("addUser===>" + userEntity.toString());
        userRepository.save(userEntity);
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
        return resultMsg;
    }

    /**
     * 添加用户 POST 请求
     * 方法二:form-data | x-www-form-urlencoded
     *
     * @param userEntity
     * @return
     */
    @Transactional
    @PostMapping("/adduser2")
    public Object addUser2(User userEntity) {
        String userId = UUID.randomUUID().toString().replaceAll("-", "");
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        userEntity.setUserId(userId);
        userEntity.setToken(token);
        System.out.println("addUser2===>" + userEntity.toString() + "|userId==" + userId);
        userRepository.save(userEntity);
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
        return resultMsg;
    }

    /**
     * 修改用户信息
     *
     * @param userEntity
     * @return
     */
    @Transactional
    @PostMapping("/updateuser")
    public Object updateUser(User userEntity) {
        User user = userRepository.findByUserId(userEntity.getUserId());
        if (user != null) {
            if (!StringUtils.isNullOrEmpty(userEntity.getName())) {
                user.setName(userEntity.getName());
            }

            if (!StringUtils.isNullOrEmpty(userEntity.getImg())) {
                user.setImg(userEntity.getImg());
            }

            if (!StringUtils.isNullOrEmpty(userEntity.getPhone())) {
                user.setPhone(userEntity.getPhone());
            }

            if (!StringUtils.isNullOrEmpty(userEntity.getSign())) {
                user.setSign(user.getSign());
            }
            userRepository.save(user);
        }
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), user);
        return resultMsg;
    }

    /**
     * 删除用户信息
     *
     * @param userId
     * @return
     */
    @Transactional
    @RequestMapping("/deleteuser")
    public Object deleteUser(String userId) {
        userRepository.deleteByUserId(userId);
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), null);
        return resultMsg;
    }
}
