package com.limaila.cloud;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author: huangxincheng
 * <p> @SpringBootApplication
 * <p> @EnableTransactionManagement(order = Integer.MAX_VALUE - 1) 确保事务在Aop最后执行
 * <p> @EnableMethodCache(basePackages = "com.limaila.cloud") Jetcache 允许方法缓存
 * <p> @EnableCreateCacheAnnotation Jetcache 允许创建缓存
 * <p> @EnableAsync 启用@Async
 * <p> @EnableScheduling @启用@Scheduled
 **/
@SpringBootApplication
@EnableTransactionManagement(order = Integer.MAX_VALUE - 1)
@EnableMethodCache(basePackages = "com.limaila.cloud")
@EnableCreateCacheAnnotation
@EnableAsync
@EnableScheduling
public class UserApplication {

    /** Set the ThreadPoolExecutor's core pool size. */
    private int corePoolSize = 10;
    /** Set the ThreadPoolExecutor's maximum pool size. */
    private int maxPoolSize = 200;
    /** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
    private int queueCapacity = 10;

    @Bean
    public Executor mySimpleAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("MySimpleExecutor-");
        executor.initialize();
        return executor;
    }

    @Bean
    public Executor myAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("MyExecutor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
