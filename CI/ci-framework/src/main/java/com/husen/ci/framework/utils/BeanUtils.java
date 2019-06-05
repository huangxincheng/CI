package com.husen.ci.framework.utils;

import java.util.Optional;

/***
 @Author:MrHuang
 @Date: 2019/6/5 22:05
 @DESC: TODO
 @VERSION: 1.0
 ***/
public class BeanUtils {

    public static <T,F> F copyProperties(T t, F f) {
        BeanUtils.copyProperties(t, f);
        return f;
    }
}
