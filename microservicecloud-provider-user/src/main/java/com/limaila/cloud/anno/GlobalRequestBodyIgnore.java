package com.limaila.cloud.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 全局统一请求忽略
 * @Target(value = {ElementType.METHOD, ElementType.TYPE}) 主键可标记在类和方法上
 * @Retention(RetentionPolicy.RUNTIME) 作用于运行期
 * @Author huangxincheng
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface GlobalRequestBodyIgnore {

    /**
     * 默认忽略
     * @return truue
     */
    boolean ignore() default true;
}
