package com.husen.ci.user.service;

import com.husen.ci.user.dao.UserDao;
import com.husen.ci.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/***
 @Author:MrHuang
 @Date: 2019/6/4 18:02
 @DESC: TODO
 @VERSION: 1.0
 ***/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getOneById(Long userId) {
        return Optional.ofNullable(userDao.findById(userId))
                .map(uto -> new User().setUserId(uto.getUserId())
                    .setUserName(uto.getUserName())
                    .setUserStatus(uto.getUserStatus())
                    .setUserActiveTime(uto.getUserActiveTime())
                    .setUserCreateTime(uto.getUserCreateTime()))
                .orElse(null);
    }

    @Override
    public Collection<User> getAll() {
         return Optional.ofNullable(userDao.getAll())
                .orElse(new ArrayList<>())
                .stream().map(uto -> new User().setUserId(uto.getUserId())
                .setUserName(uto.getUserName())
                .setUserStatus(uto.getUserStatus())
                .setUserActiveTime(uto.getUserActiveTime())
                .setUserCreateTime(uto.getUserCreateTime())).collect(Collectors.toList());
    }
}
