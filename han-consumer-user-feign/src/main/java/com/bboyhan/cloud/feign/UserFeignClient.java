package com.bboyhan.cloud.feign;

import com.bboyhan.cloud.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 17:25
 */
@FeignClient("han-provider-user")
@Component
public interface UserFeignClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User getUser(@PathVariable("id") Long id);

}
