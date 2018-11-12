package com.limaila.cloud;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import static springfox.documentation.builders.PathSelectors.regex;

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
@EnableSwagger2
public class UserApplication {



    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    /**
     * Swagger2Config
     * @return
     */
    @Bean
    public Docket accessToken() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("api")// 定义组
                .select() // 选择那些路径和api会生成document
                // 拦截的包路径
                .apis(RequestHandlerSelectors.basePackage("com.limaila.cloud"))
                // 拦截的接口路径
//                .paths(regex("/api/.*"))
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    /**
     * Swagger2Config
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("Swagger")// 标题
                .description("Swagger desc")// 描述
                .termsOfServiceUrl("http://www.limaila.com")//
                .contact(new Contact("huangxincheng", "www.limaila.com", "249270087@qq.com"))// 联系
                //.license("Apache License Version 2.0")// 开源协议
                //.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")// 地址
                .version("1.0")// 版本
                .build();
    }





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
    public ClientHttpRequestFactory factory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000);//单位为ms
        factory.setConnectTimeout(10000);//单位为ms
        //启用代理
//        SocketAddress address = new InetSocketAddress("114.215.174.227", 8080);
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
//        factory.setProxy(proxy);
        return factory;
    }




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



}
