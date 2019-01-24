package com.bboyhan.cloud.service;

import com.bboyhan.cloud.domain.User;

import java.util.Optional;

/**
 * @Auther: wushaohan
 * @Date: 2019/1/14 10:42
 * @Description:
 */
public interface UserService {

    Optional<User> getUser(Long id);
}
