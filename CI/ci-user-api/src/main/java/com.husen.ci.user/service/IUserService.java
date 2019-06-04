package com.husen.ci.user.service;

import com.husen.ci.user.pojo.User;

import java.util.Collection;

/***
 @Author:MrHuang
 @Date: 2019/6/4 17:17
 @DESC: TODO
 @VERSION: 1.0
 ***/
public interface IUserService {

    User getOneById(Long userId);

    Collection<User> getAll();

}
