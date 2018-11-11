package com.limaila.cloud.proxy;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Author: huangxincheng
 * <p> http://ip.zdaye.com/FreeIPlist.html
 *      此网站可以获得免费的代理IP
 * <p>
 **/
public class ProxyCustomizer implements RestTemplateCustomizer {
    @Override
    public void customize(RestTemplate restTemplate) {
        String proxyHost = "47.107.79.51";
        int proxyPort = 3128;
        HttpHost proxy = new HttpHost(proxyHost, proxyPort);
        HttpClient httpClient = HttpClientBuilder.create().setRoutePlanner(new DefaultProxyRoutePlanner(proxy) {
            @Override
            public HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {

                return super.determineProxy(target, request, context);
            }
        }).build();
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(10000);
        httpComponentsClientHttpRequestFactory.setReadTimeout(60000);
        restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
    }
}
