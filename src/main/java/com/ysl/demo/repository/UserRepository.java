package com.ysl.demo.repository;

import com.ysl.demo.bean.User;
import org.springframework.data.repository.CrudRepository;
/**
 * 添加数据访问接口类 UserRepository.java
 * Created by YSL on 2018/8/7.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserId(String userId);
    User findByUserIdAndToken(String userId,String token);
    void deleteByUserId(String userId);
}
