package com.husen.ci.order.service;

import com.husen.ci.order.pojo.Order;

/***
 @Author:MrHuang
 @Date: 2019/6/4 20:04
 @DESC: TODO
 @VERSION: 1.0
 ***/
public interface IOrderSevrice {

    boolean saveOrder(Order order);

    Order queryOrder(Long orderNo);
}
