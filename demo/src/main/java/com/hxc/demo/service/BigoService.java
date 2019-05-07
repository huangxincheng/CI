package com.hxc.demo.service;

import com.hxc.demo.aop.Bigo;
import org.springframework.stereotype.Service;

/***
 @Author:MrHuang
 @Date: 2019/5/7 17:48
 @DESC: TODO
 @VERSION: 1.0
 ***/
@Bigo
@Service
public class BigoService {

    @Bigo
    public void h1() {
        System.out.println("-----------------h1");
    }
    public void h2() {
        System.out.println("----------------h2");
    }
}
