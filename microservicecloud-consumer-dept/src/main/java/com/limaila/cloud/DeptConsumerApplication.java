package com.limaila.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@SpringBootApplication
public class DeptConsumerApplication {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        RestTemplate restTemplate = new RestTemplate(factory);
//        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        /**
         * restTemplate作为spring web client下的一个工具类 对http请求做了一层封装，用起来也更加简洁容易，
         * 但最近遇到一个问题就是在发送请求时由于请求中包含中文导致乱码，都变成???????一堆问号，网上很多解决方案，但很多都比较…..
         * 可以看到StringHttpMessageConverter默认是ISO-8859-1编码（有空可以看下别的MessageConverter其实都是UTF-8的），所以就把它改了就好啦~
         * 先看说如何解决：
         * restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
         */
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    /**
     * 配置ClientHttpRequestFactory
     * @return
     */
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000);//单位为ms
        factory.setConnectTimeout(10000);//单位为ms
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerApplication.class, args);
    }
}
