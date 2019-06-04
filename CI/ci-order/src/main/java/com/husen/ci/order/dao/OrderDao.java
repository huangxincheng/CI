package com.husen.ci.order.dao;

import com.husen.ci.order.entity.OrderDTO;
import com.husen.ci.order.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/***
 @Author:MrHuang
 @Date: 2019/6/4 20:12
 @DESC: TODO
 @VERSION: 1.0
 ***/
@Repository
public class OrderDao {

    public static final Map<Long, OrderDTO> ORDER_MAP = new HashMap<>();

    public boolean saveOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return false;
        }
        if (ORDER_MAP.containsKey(orderDTO.getOrderNo())) {
            return false;
        }
        ORDER_MAP.put(orderDTO.getOrderNo(), orderDTO);
        return true;
    }

    public OrderDTO queryOrder(Long orderNo) {
        return ORDER_MAP.get(orderNo);
    }
}
