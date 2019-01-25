package com.bboyhan.server.service.impl;

import com.bboyhan.server.model.User;
import com.bboyhan.server.repository.UserRepository;
import com.bboyhan.server.service.UserService;
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
