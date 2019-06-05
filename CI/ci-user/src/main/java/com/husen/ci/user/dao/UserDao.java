package com.husen.ci.user.dao;

import com.husen.ci.user.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/***
 @Author:MrHuang
 @Date: 2019/6/4 18:16
 @DESC: TODO
 @VERSION: 1.0
 ***/
@Repository
public class UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public UserDTO findById(String userId) {
        UserDTO dto = mongoTemplate.findById(userId, UserDTO.class);
        return dto;
    }

    public List<UserDTO> getAll() {
        List<UserDTO> all = mongoTemplate.findAll(UserDTO.class);
        return all;
    }

    public boolean add(UserDTO userDTO) {
        UserDTO insert = mongoTemplate.insert(userDTO);
        return true;
    }
}
