package com.husen.ci.framework.api;

import com.husen.ci.framework.json.JSONUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

import static com.husen.ci.framework.api.GlobalApiCode.*;

/***
 @Author:MrHuang
 @Date: 2019/6/13 15:03
 @DESC: TODO
 @VERSION: 1.0
 ***/
@Data
@Accessors(chain = true)
@Slf4j
public class GlobalApiResponse<T> implements Serializable {

    /**
     * 业务响应Code
     */
    private int code;

    /**
     * 业务响应Msg与Code对应
     */
    private String msg;

    /**
     * 业务响应体payLoad
     */
    private T payLoad;

    /**
     * 成功时候返回
     *
     * @param payLoad
     * @param <T>
     * @return
     */
    public static <T> GlobalApiResponse<T> toSuccess(T payLoad) {
        log.info("The GlobalApiResponse toSuccess payLoad = {}", JSONUtils.object2Json(payLoad));
        return new GlobalApiResponse<T>().setCode(SUCCESS_CODE).setMsg("SUCCESS").setPayLoad(payLoad);
    }

    /**
     * 失败时候返回
     * @return
     */
    public static GlobalApiResponse toFail(Exception e) {
        log.error("The GlobalApiResponse toFail", e);
        return new GlobalApiResponse().setCode(UNKNOW_CODE).setMsg("UNKONW");
    }

    /**
     * 失败时返回
     *
     * @param code
     * @param errorMsg
     * @return
     */
    public static GlobalApiResponse toFail(int code, String errorMsg) {
        log.info("The GlobalApiResponse toFail code = {} errorMsg = {}", code, errorMsg);
        return new GlobalApiResponse().setCode(code).setMsg(errorMsg);
    }
}
