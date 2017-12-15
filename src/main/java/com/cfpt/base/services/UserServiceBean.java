package com.cfpt.base.services;

import com.cfpt.base.mapper.UserMapper;
import com.cfpt.base.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dl on 2017-12-15.
 */
@Service
public class UserServiceBean implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByName(username);
    }
}
