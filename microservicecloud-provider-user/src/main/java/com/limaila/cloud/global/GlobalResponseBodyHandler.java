package com.limaila.cloud.global;

import com.limaila.cloud.anno.GlobalResponseBodyIgnore;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        GlobalResponseBodyIgnore a1 = AnnotationUtils.findAnnotation(methodParameter.getContainingClass(), GlobalResponseBodyIgnore.class);
        GlobalResponseBodyIgnore a2 = methodParameter.getMethodAnnotation(GlobalResponseBodyIgnore.class);
        System.out.println("a1 = " + a1);
        System.out.println("a2 = " + a2);
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return null;
    }
}
