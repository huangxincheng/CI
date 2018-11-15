package com.limaila.cloud.global;

import com.limaila.cloud.global.result.GlobalResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public GlobalResult errorHandler(Exception ex) {
        log.error("[errorHandler] ============ " ,ex);
        return GlobalResult.toFail(ex.getMessage());
    }
}
