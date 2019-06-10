package com.husen.ci.framework.net;

import com.alibaba.fastjson.JSON;
import com.husen.ci.framework.net.bean.HttpResult;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/***
 @Author:MrHuang
 @Date: 2019/6/6 17:18
 @DESC: TODO Http请求工具类
 @VERSION: 1.0
 ***/
@Slf4j
public class HttpUtils {

    public volatile static HttpUtils instance;

    private static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    private static final String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";

    private MediaType applicationJsonMediaType = MediaType.parse(APPLICATION_JSON_UTF8_VALUE);

    private MediaType applicationFormMediaType = MediaType.parse(APPLICATION_FORM_URLENCODED_VALUE);

    private OkHttpClient okHttpClient;

    /**
     * 单例模式获取OkHttp3Util
     * @return
     */
    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    private HttpUtils() {
        okhttp3.OkHttpClient.Builder ClientBuilder = new okhttp3.OkHttpClient.Builder();
        ClientBuilder.readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(50, 5, TimeUnit.MINUTES));
//        支持HTTPS请求，跳过证书验证
        ClientBuilder.sslSocketFactory(createSSLSocketFactory(), new MyX509TrustManager());
        ClientBuilder.hostnameVerifier((s, sslSession) -> true);
        okHttpClient = ClientBuilder.build();
    }


    private HttpResult toResult(Response response) throws IOException {
        HttpResult result = new HttpResult().setCode(response.code())
                .setContent(response.body() != null ? response.body().string() : null)
                .setHeaders(response.headers().toMultimap());
        return result;
    }

    /**
     * 封装同步执行方法
     * @param call
     * @return
     */
    private HttpResult syncExecute(Call call) {
        try {
            Response response = call.execute();
            return this.toResult(response);
        } catch (IOException e) {
            log.error("HttpUtils execute fail",e);
        }
        return null;
    }

    /**
     * 生成BodyBuilder
     * @param headers
     * @return
     */
    private Request.Builder bodyBuilder(Map<String,Serializable> headers) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            headers.forEach((s, o) -> {
                builder.addHeader(s, o.toString());
            });
        }
        return builder;
    }

    /**
     * 生成FormBodyBuilder
     * @param headers
     * @return
     */
    private Request.Builder formBodyBuilder(Map<String,Serializable> headers) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            headers.forEach((s, o) -> {
                builder.addHeader(s, o.toString());
            });
        }
        return builder;
    }


    /**
     * 封装异步执行方法
     * @param call
     * @param callback
     */
    private void asyncExecute(Call call, Callback callback) {
        call.enqueue(callback);
    }


    /**
     * 发起同步Get请求
     * @param url
     * @return
     * @throws IOException
     */
    public HttpResult doGet(String url) {
        Request request = new Request.Builder().get().url(url).build();
        Call call = okHttpClient.newCall(request);
        return syncExecute(call);
    }

    /**
     * 发起异步Get请求
     * @param url
     * @param callback
     */
    public void doGetWithAsync(String url, Callback callback) {
        Request request = new Request.Builder().get().url(url).build();
        Call call = okHttpClient.newCall(request);
        asyncExecute(call, callback);
    }

    /**
     * 发起同步Post Body请求
     * @param url
     * @param bodyData
     * @param headers
     * @return
     */
    public HttpResult doPostBody(String url, String bodyData, Map<String, Serializable> headers) {
        RequestBody body = RequestBody.create(applicationJsonMediaType, bodyData);
        Request.Builder builder = bodyBuilder(headers);
        Request request = builder.post(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        return syncExecute(call);
    }

    /**
     * 发起异步Post Body请求
     * @param url
     * @param bodyData
     * @param headers
     * @param callback
     */
    public void doPostBodyWithAsync(String url, String bodyData, Map<String, Serializable> headers, Callback callback) {
        RequestBody body = RequestBody.create(applicationJsonMediaType, bodyData);
        Request.Builder builder = bodyBuilder(headers);
        Request request = builder.post(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        asyncExecute(call, callback);
    }

    /**
     * 发起同步FormBody 请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public HttpResult doPostFormBody(String url, Map<String, Serializable> params, Map<String, Serializable> headers)  {
        RequestBody formBody = FormBody.create(applicationFormMediaType, JSON.toJSONString(params));
        Request.Builder builder = formBodyBuilder(headers);
        Request request = builder.post(formBody).url(url).build();
        Call call = okHttpClient.newCall(request);
        return syncExecute(call);
    }

    /**
     * 发起异步FormBody 请求
     * @param url
     * @param params
     * @param headers
     * @param callback
     */
    public void doPostFormBodyWithAsync(String url, Map<String, Serializable> params, Map<String, Serializable> headers, Callback callback) {
        RequestBody formBody = FormBody.create(applicationFormMediaType, JSON.toJSONString(params));
        Request.Builder builder = formBodyBuilder(headers);
        Request request = builder.post(formBody).url(url).build();
        Call call = okHttpClient.newCall(request);
        asyncExecute(call, callback);
    }

    /**
     * 发起同步Put Body请求
     * @param url
     * @param bodyData
     * @param headers
     * @return
     */
    public HttpResult doPutBody(String url, String bodyData, Map<String,Serializable> headers)  {
        RequestBody body = RequestBody.create(applicationJsonMediaType, bodyData);
        Request.Builder builder = bodyBuilder(headers);
        Request request = builder.put(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        return syncExecute(call);
    }

    /**
     * 发起异步Body 请求
     * @param url
     * @param bodyData
     * @param headers
     * @param callback
     */
    public void doPutBodyWithAsync(String url, String bodyData, Map<String,Serializable> headers, Callback callback)  {
        RequestBody body = RequestBody.create(applicationJsonMediaType, bodyData);
        Request.Builder builder = bodyBuilder(headers);
        Request request = builder.put(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        asyncExecute(call, callback);
    }

    /**
     * 发起同步delete请求
     * @param url
     * @param headers
     * @return
     */
    public HttpResult doDelete(String url, Map<String, Serializable> headers)  {
        Request.Builder builder = bodyBuilder(headers);
        Request request = builder.delete().url(url).build();
        Call call = okHttpClient.newCall(request);
        return syncExecute(call);
    }

    /**
     * 发起同步delete body请求
     * @param url
     * @param bodyData
     * @param headers
     * @return
     */
    public HttpResult doDeleteBody(String url, String bodyData, Map<String,Serializable> headers)  {
        RequestBody body = RequestBody.create(applicationJsonMediaType, bodyData);
        Request.Builder builder = bodyBuilder(headers);
        Request request = builder.delete(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        return syncExecute(call);
    }

    /**
     * 发起异步delete请求
     * @param url
     * @param headers
     * @param callback
     */
    public void doDeleteWithAsync(String url, Map<String, Serializable> headers, Callback callback) {
        Request.Builder builder = bodyBuilder(headers);
        Request request = builder.delete().url(url).build();
        Call call = okHttpClient.newCall(request);
        asyncExecute(call, callback);
    }

    /**
     * 发起异步delete body请求
     * @param url
     * @param bodyData
     * @param headers
     * @param callback
     */
    public void doDeleteBodyWithAsync(String url, String bodyData, Map<String,Serializable> headers, Callback callback) {
        RequestBody body = RequestBody.create(applicationJsonMediaType, bodyData);
        Request.Builder builder = bodyBuilder(headers);
        Request request = builder.delete(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        asyncExecute(call, callback);
    }



    /**
     * 通过异步方式采用DELETE方法JSON方式获取数据
     * @param url
     * @return
     */
    public void deleteJsonDataForSync(String url, Map<String, Serializable> headers, Callback callback) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            headers.forEach((s, o) -> {
                builder.addHeader(s, o.toString());
            });
        }
        Request request = builder.delete().url(url).build();
        Call call = okHttpClient.newCall(request);
        asyncExecute(call, callback);
    }





    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     *
     * @return
     */
    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new MyX509TrustManager()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }

    /**
     * 用于信任所有证书
     */
    class MyX509TrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static void main(String[] args) {
        HttpResult result = HttpUtils.getInstance().doGet("http://www.baidu.com");
        System.out.println(result);
    }
}