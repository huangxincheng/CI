package com.husen.ci.framework.cache;

import com.google.common.cache.Cache;

/***
 @Author:MrHuang
 @Date: 2019/6/10 22:14
 @DESC: TODO 基于Guava的本地缓存Utils
 @VERSION: 1.0
 ***/
public class LocalCacheUtils<K,V> {

    private LocalCacheUtils(){}

    private Cache<K, V> cache;
}
