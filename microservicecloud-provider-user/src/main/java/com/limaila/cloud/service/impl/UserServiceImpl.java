package com.limaila.cloud.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.limaila.cloud.dao.UserMapper;
import com.limaila.cloud.entitys.User;
import com.limaila.cloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @CacheInvalidate(name="userCache.", key="'_'.concat(#userno)")
    @Override
    public int delete(Long userno) {
        return userMapper.delete(userno);
    }

    @CacheUpdate(name="userCache.", key="'_'.concat(#user.userno)", value="#user")
    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Cached(name="userCache.", key="'_'.concat(#userno)", expire = 3600)
    @Override
    public User get(Long userno) {
        return userMapper.get(userno);
    }

}
