package com.limaila.cloud.service;

import com.limaila.cloud.entitys.User;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
public interface UserService {

    int add(User user);

    int delete(Long userno);

    int update(User user);

    User get(Long userno);
}
