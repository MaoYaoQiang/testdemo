package com.example.testdemo.service.impl;

import com.example.testdemo.service.Shape;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("画一个三角的图形");
    }
}
