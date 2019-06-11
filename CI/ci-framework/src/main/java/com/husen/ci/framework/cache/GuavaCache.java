package com.husen.ci.framework.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.husen.ci.framework.net.bean.HttpResult;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/***
 @Author:MrHuang
 @Date: 2019/6/11 12:32
 @DESC: TODO
 @VERSION: 1.0
 ***/
public class GuavaCache<K,V> {

    /**
     * 由于cahce.getIfPresent
     */
    private Cache<K, Optional<V>> cache;

    /**
     * 默认失效时间
     */
    private static final int DEFAULT_EXPIRE_SECONDS = 60;

    /**
     * 最大存储SIZE
     */
    private static final long MAX_IMUM_SIZE = 5000;

    private GuavaCache() {
       this(DEFAULT_EXPIRE_SECONDS, MAX_IMUM_SIZE);
    }

    private GuavaCache(int seconds, long maxSize) {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(seconds, TimeUnit.SECONDS)
                .maximumSize(maxSize)
                .build();
    }

    /**
     * 获取实例
     * 建议通过这种形式获取
     * @return
     */
    public static <K,V> GuavaCache<K,V> getInstace() {
        return new GuavaCache<>();
    }

    /**
     * 获取实例
     * @param seconds 失效时间
     * @param maxSize 最大存储SIZE
     * @return
     */
    public static <K,V> GuavaCache<K,V> getInstace(int seconds, long maxSize) {
        return new GuavaCache<>(seconds, maxSize);
    }

    private Cache<K, Optional<V>> getCache() {
        return this.cache;
    }

    /**
     * 如果有值返回值，如果没有值返回空
     * @param key
     * @return
     */
    private Optional<V> getLocal(K key) {
        return getCache().getIfPresent(key);
    }

    /**
     * 由于Guava的Callable接口中，若采用过期机制，
     * 如果自带的Callable返回了null，get(xx,CallAble)便会抛出异常： CacheLoader returned null for key
     * 故采用Optional + 额外的Supplier
     */
    public V get(K key, Supplier<V> call) {
        Optional<V> local = getLocal(key);
        if (local == null) {
            V v = call.get();
            getCache().put(key, Optional.ofNullable(v));
            return v;
        }
        return local.orElse(null);
    }

    public static void main(String[] args) {
        GuavaCache<String,HttpResult> cache = GuavaCache.getInstace();
        HttpResult result = cache.get("123", () -> new HttpResult().setContent("213"));
    }
}
