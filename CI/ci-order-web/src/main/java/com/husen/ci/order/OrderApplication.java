package com.husen.ci.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/***
 @Author:MrHuang
 @Date: 2019/6/4 20:25
 @DESC: TODO
 @VERSION: 1.0
 ***/
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
