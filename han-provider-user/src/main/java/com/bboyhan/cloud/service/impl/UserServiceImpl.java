package com.bboyhan.cloud.service.impl;

import com.bboyhan.cloud.domain.User;
import com.bboyhan.cloud.repository.UserRepository;
import com.bboyhan.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Auther: wushaohan
 * @Date: 2019/1/14 10:54
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
}
