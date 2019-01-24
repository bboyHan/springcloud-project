package com.bboyhan.cloud.controller;

import com.bboyhan.cloud.domain.User;
import com.bboyhan.cloud.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wushaohan
 * @Date: 2019/1/16 08:42
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/feign/{id}")
    public User getUser(@PathVariable Long id) {
        return userFeignClient.getUser(id);
    }
}
