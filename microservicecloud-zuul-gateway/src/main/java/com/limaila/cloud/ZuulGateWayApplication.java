package com.limaila.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@SpringBootApplication
@EnableZuulProxy
public class ZuulGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGateWayApplication.class, args);
    }
}
