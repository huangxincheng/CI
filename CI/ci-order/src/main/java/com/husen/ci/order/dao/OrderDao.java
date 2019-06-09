package com.husen.ci.order.dao;

import com.husen.ci.framework.utils.IpUtils;
import com.husen.ci.framework.utils.SnowflakeIdWorker;
import com.husen.ci.order.entity.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

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
        orderDTO.setHost(IpUtils.getServerHost());
        OrderDTO insert = mongoTemplate.insert(orderDTO);
        return true;
    }

    public OrderDTO queryOrder(Long orderNo) {
       return  mongoTemplate.findById(orderNo, OrderDTO.class);
    }
}
