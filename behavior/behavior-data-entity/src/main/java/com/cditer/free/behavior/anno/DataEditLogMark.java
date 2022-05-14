package com.cditer.free.behavior.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface DataEditLogMark {
    /**
     * 业务类型
     */
    String bsnType();

    /**
     * 记录详情
     */
    boolean recordDtl() default false;

    /**
     * 空详情是否记录
     */
    boolean emptyDtlRecord() default false;
}
