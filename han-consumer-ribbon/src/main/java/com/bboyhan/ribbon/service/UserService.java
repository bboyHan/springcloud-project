package com.bboyhan.ribbon.service;

import com.bboyhan.common.enums.ResultCodeEnum;
import com.bboyhan.common.util.Result;
import com.bboyhan.ribbon.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/25 19:20
 */
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @HystrixCommand(fallbackMethod = "userFallback")
    @CacheResult
    public Result user(String id) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("han-provider-user");
        System.out.println(serviceInstance.getHost() + " : " +serviceInstance.getPort());
        User user = restTemplate.getForEntity("http://han-provider-user/user/" + id, User.class).getBody();
        return Result.success(user);
    }

    public Result userFallback(String id) {
        return Result.error(ResultCodeEnum.BUSY);
    }
}
