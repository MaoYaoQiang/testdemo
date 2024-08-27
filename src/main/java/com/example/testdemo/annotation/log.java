package com.example.testdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface log {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
     public String businessType() default "其他";

    /**
     * 操作人类别
     */
    public String operatorType() default "";

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
