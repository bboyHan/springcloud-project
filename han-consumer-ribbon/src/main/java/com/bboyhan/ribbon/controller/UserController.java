package com.bboyhan.ribbon.controller;

import com.bboyhan.common.util.Result;
import com.bboyhan.ribbon.service.UserService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/25 18:14
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/ribbon/{id}")
    public Result hello(@PathVariable String id) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Result users = userService.user(id);
        context.close();
        return users;
    }


}
