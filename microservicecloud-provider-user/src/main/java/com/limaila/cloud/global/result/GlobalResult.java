package com.limaila.cloud.global.result;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class GlobalResult {

    private int code;

    private String msg;

    private Object data;

    public GlobalResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static GlobalResult toSucc(Object data) {
        return new GlobalResult(GlobalEnum.SUCC.getCode(), GlobalEnum.SUCC.getMsg(), data);
    }
}
