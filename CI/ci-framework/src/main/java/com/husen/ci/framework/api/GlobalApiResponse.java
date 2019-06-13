package com.husen.ci.framework.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/***
 @Author:MrHuang
 @Date: 2019/6/13 15:03
 @DESC: TODO
 @VERSION: 1.0
 ***/
@Data
@Accessors(chain = true)
public class GlobalApiResponse<T> implements Serializable {

    private int code;

    private String msg;

    private T payLoad;

    public static final int SUCCESS_CODE = 200;

    public static final int FAIL_CODE = 500;

    /**
     * 成功时候返回
     *
     * @param payLoad
     * @param <T>
     * @return
     */
    public static <T> GlobalApiResponse<T> toSuccess(T payLoad) {
        return new GlobalApiResponse<T>().setCode(SUCCESS_CODE).setMsg("SUCCESS").setPayLoad(payLoad);
    }

    /**
     * 失败时候返回
     *
     * @param msg
     * @return
     */
    public static GlobalApiResponse toFail(String msg) {
        return new GlobalApiResponse().setCode(FAIL_CODE).setMsg(msg);
    }
}
