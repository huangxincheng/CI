package com.husen.ci.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.ImportResource;

/***
 @Author:MrHuang
 @Date: 2019/6/4 18:47
 @DESC: TODO
 @VERSION: 1.0
 ***/
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
@ComponentScans(value = {@ComponentScan("com.husen.ci.framework")})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
