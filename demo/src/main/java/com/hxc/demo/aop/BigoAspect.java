package com.hxc.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/***
 @Author:MrHuang
 @Date: 2019/5/7 16:19
 @DESC: TODO 定义Bigo切面
 @VERSION: 1.0
 ***/
@Slf4j
@Aspect
@Component
public class BigoAspect {

    /**
     * 定义切入点
     * @within(com.hxc.demo.aop.Bigo) 如果类上有Bigo注解就能切入
     */
    @Pointcut("@within(com.hxc.demo.aop.Bigo)")
    public void bigoAnnotationPointcut() {}

    /**\
     * 定义切入点
     * @annotation(com.hxc.demo.aop.Bigo) 如果方法上有Bigo注解就能切入
     */
    @Pointcut("@annotation(com.hxc.demo.aop.Bigo)")
    public void bigoAnnotationPointcut2() {}


    @Pointcut("execution(* com.hxc.demo.service..*.*(..))")
    public void bigoAnnotationPointcut3() { }

    /**
     * 定义前置通知
     */
    @Before("bigoAnnotationPointcut()")
    public void befoAdvice() {
        System.out.println("beforeAdvice");
    }

    /**
     * 定义后置通知
     */
    @After("bigoAnnotationPointcut2()")
    public void afterAdvice() {
        System.out.println("afterAdvice");
    }

    /**
     * 定义环绕通知
     * @param joinPoint
     * @return
     */
    @Around("bigoAnnotationPointcut3()")
    public Object aroundAdvice(final ProceedingJoinPoint joinPoint) {
        Method method = null;
        if (joinPoint.getSignature() instanceof MethodSignature) {

        }
        System.out.println("aroundAdvice1");
        Object result = null;
        try {
             result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("aroundAdvice2");
        return result;
    }

}
