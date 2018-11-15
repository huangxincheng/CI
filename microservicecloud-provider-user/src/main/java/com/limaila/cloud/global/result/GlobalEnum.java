package com.limaila.cloud.global.result;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum GlobalEnum {

    SUCC(200, "SUCCESS");

    private Integer code;

    private String msg;

    private GlobalEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
