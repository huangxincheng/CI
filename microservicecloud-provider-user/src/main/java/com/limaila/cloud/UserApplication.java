package com.limaila.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Author: huangxincheng
 * <p> @EnableTransactionManagement(order = Integer.MAX_VALUE - 1) 确保事务在Aop最后执行
 * <p>
 **/
@EnableTransactionManagement(order = Integer.MAX_VALUE - 1)
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
