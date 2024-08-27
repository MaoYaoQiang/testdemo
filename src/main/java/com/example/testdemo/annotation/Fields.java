package com.example.testdemo.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Fields {
    String name() default "";
    int index() default 0;
}
