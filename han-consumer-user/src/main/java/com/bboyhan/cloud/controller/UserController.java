package com.bboyhan.cloud.controller;

import com.bboyhan.cloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: wushaohan
 * @Date: 2019/1/14 13:46
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/rest/user/{id}")
    public User getUser(@PathVariable Long id) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("han-provider-user");
        System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

        return restTemplate.getForObject("http://han-provider-user/user/" + id, User.class);
    }
}
