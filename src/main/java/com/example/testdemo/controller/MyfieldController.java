package com.example.testdemo.controller;

import com.example.testdemo.annotation.Myfield;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
@RequestMapping("/field")
@RestController
public class MyfieldController {
    @Myfield(desc = "测试",length = 12)
    private String name;
    @RequestMapping("/myfiled")
    public String antioTest(){
        Class clazz = MyfieldController.class;
        for (Field declaredField : clazz.getDeclaredFields()) {
            if(declaredField.isAnnotationPresent(Myfield.class)){
                Myfield annotation = declaredField.getAnnotation(Myfield.class);
                System.out.println(annotation.desc());
                System.out.println(annotation.length());
                System.out.println(annotation.toString());
                return annotation.toString();
            }
        }
        return null;
    }
}
