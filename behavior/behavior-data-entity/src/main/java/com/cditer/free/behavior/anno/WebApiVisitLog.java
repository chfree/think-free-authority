package com.cditer.free.behavior.anno;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface WebApiVisitLog {
    /**
     * 日志类型
     */
    String type();

    /**
     * id所属的参数名，取request
     */
    String bsnIdOfParamName() default "";

    /**
     * 是否记录url
     * 理论上记录日志类型即可
     */
    boolean recordUrl() default false;
}
