package com.husen.ci.order.dao;

import com.husen.ci.framework.utils.SnowflakeIdWorker;
import com.husen.ci.order.entity.OrderDTO;
import com.husen.ci.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;
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

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean saveOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return false;
        }
        orderDTO.setOrderNo(new SnowflakeIdWorker().nextId());
        String hostAddress = "unkown";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            if (null == localHost.getHostAddress()) {
                hostAddress = "undefine";
            } else {
                hostAddress = localHost.getHostAddress();
            }
        } catch (Exception e) {}
        orderDTO.setHost(hostAddress);
        OrderDTO insert = mongoTemplate.insert(orderDTO);
        return true;
    }

    public OrderDTO queryOrder(Long orderNo) {
       return  mongoTemplate.findById(orderNo, OrderDTO.class);
    }
}
