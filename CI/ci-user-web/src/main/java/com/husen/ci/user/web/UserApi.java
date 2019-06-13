package com.husen.ci.user.web;

import com.husen.ci.framework.api.GlobalApiResponse;
import com.husen.ci.order.pojo.Order;
import com.husen.ci.order.service.IOrderSevrice;
import com.husen.ci.user.pojo.User;
import com.husen.ci.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/***
 @Author:MrHuang
 @Date: 2019/6/4 18:38
 @DESC: TODO
 @VERSION: 1.0
 ***/
@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserApi {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderSevrice orderSevrice;

    @RequestMapping("/get/{userId}")
    public GlobalApiResponse<User> get(@PathVariable String userId) {
        log.info("UserApi get userId = " + userId);
        return GlobalApiResponse.toSuccess(userService.getOneById(userId));
    }

    @RequestMapping("/getAll")
    public GlobalApiResponse<Collection<User>> getAll() {
        log.info("UserApi getAll");
        return GlobalApiResponse.toSuccess(userService.getAll());
    }

    @RequestMapping("/saveUser/{userName}")
    public User saveUser(@PathVariable String userName) {
        User user = new User().setUserName(userName);
        return userService.createUser(user);
    }

    @RequestMapping("/saveOrder")
    public boolean saveOrder(@RequestBody Order order) {
        boolean flag = orderSevrice.saveOrder(order);
        return flag;
    }

    @RequestMapping("/queryOrder/{orderNo}")
    public Order queryOrder(@PathVariable Long orderNo) {
        Order order = orderSevrice.queryOrder(orderNo);
        return order;
    }


}
