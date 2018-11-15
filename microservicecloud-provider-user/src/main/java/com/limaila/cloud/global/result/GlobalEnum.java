package com.limaila.cloud.global.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public enum GlobalEnum {

    SUCC(200, "SUCCESS"),

    FAIL(500, "FAIL"),

    ;

    private Integer code;

    private String msg;

    GlobalEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
