package com.limaila.cloud.service.impl;

import com.limaila.cloud.dao.UserMapper;
import com.limaila.cloud.entitys.User;
import com.limaila.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int delete(Long id) {
        return userMapper.delete(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User get(Long id) {
        return userMapper.get(id);
    }
}
