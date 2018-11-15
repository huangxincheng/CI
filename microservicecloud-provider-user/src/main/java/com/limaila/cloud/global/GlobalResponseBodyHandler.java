package com.limaila.cloud.global;

import com.alibaba.fastjson.JSON;
import com.limaila.cloud.anno.GlobalResponseBodyIgnore;
import com.limaila.cloud.global.result.GlobalResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        boolean flag = true;
        GlobalResponseBodyIgnore a1 = returnType.getMethodAnnotation(GlobalResponseBodyIgnore.class);
        if (a1 != null) {
            flag = !a1.ignore();
        } else {
            a1 = AnnotationUtils.findAnnotation(returnType.getContainingClass(), GlobalResponseBodyIgnore.class);
            if (a1 != null) {
                flag = !a1.ignore();
            }
        }
        log.info("[GlobalResponseBodyHandler]=========={} = {}", "supports", flag);
        return flag;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
            return JSON.toJSONString(GlobalResult.toSucc(body));
        } else if (body instanceof GlobalResult) {
            return body;
        } else {
            return GlobalResult.toSucc(body);
        }
    }
}
