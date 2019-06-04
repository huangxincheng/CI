package com.husen.ci.user.dao;

import com.husen.ci.user.entity.UserDTO;
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

    public static List<UserDTO> dtos;

    static {
        dtos = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dtos.add(new UserDTO()
                    .setUserId((long)i)
                    .setUserName("USER" + i)
                    .setUserActiveTime(LocalDateTime.now())
                    .setUserCreateTime(LocalDateTime.now())
                    .setUserStatus(ThreadLocalRandom.current().nextInt(2))
            );
        }
    }

    public UserDTO findById(Long userId) {
        for (int i = 0; i < dtos.size(); i++) {
            if (userId.equals(dtos.get(i).getUserId())) {
                return dtos.get(i);
            }
        }
        return null;
    }

    public List<UserDTO> getAll() {
        return dtos;
    }
}
