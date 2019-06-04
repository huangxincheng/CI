package com.husen.ci.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/***
 @Author:MrHuang
 @Date: 2019/6/4 18:15
 @DESC: TODO
 @VERSION: 1.0
 ***/
@Data
@Accessors(chain = true)
public class UserDTO implements Serializable {

    private Long userId;

    private String userName;

    private Integer userStatus;

    private LocalDateTime userCreateTime;

    private LocalDateTime userActiveTime;
}
