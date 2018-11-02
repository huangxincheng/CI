package com.limaila.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class DeptProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeptProviderApplication.class, args);
    }
}
