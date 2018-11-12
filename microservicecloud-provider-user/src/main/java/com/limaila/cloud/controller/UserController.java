package com.limaila.cloud.controller;

import com.limaila.cloud.entitys.User;
import com.limaila.cloud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RestController
@RequestMapping("/user")
@Api("用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ApiOperation("增加用户")
    @ApiImplicitParam(name = "user", value = "用户信息", required =true, dataType ="string")
    public int add(@RequestBody  User user) {
       return userService.add(user);
    }

    @GetMapping("/delete/{userno}")
    @ApiOperation("删除用户")
    public int delete(@PathVariable  Long userno) {
       return userService.delete(userno);
    }

    @PostMapping("/update")
    @ApiOperation("更新用户")
    @ApiImplicitParam(name = "user", value = "用户信息", required =true, dataType ="string")
    public int update(User user) {
       return userService.update(user);
    }

    @GetMapping("/get")
    @ApiOperation("查询用户")
    public User get(@RequestParam Long userno, String id) {
       return userService.get(userno);
    }
}
