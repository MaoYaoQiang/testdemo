package com.example.testdemo.service.impl;

import com.example.testdemo.service.Shape;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("画一个圆形的图形");
    }
}
