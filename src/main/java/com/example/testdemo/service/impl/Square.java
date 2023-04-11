package com.example.testdemo.service.impl;

import com.example.testdemo.service.Shape;

public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("画一个方形的图形");
    }
}
