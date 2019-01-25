package com.bboyhan.feignclient.controller;

import com.bboyhan.feignclient.model.User;
import com.bboyhan.feignclient.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/25 18:20
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
