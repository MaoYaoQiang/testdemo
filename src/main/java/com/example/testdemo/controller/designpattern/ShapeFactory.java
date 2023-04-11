package com.example.testdemo.controller.designpattern;

import com.example.testdemo.service.Shape;
import com.example.testdemo.service.impl.Circle;
import com.example.testdemo.service.impl.Rectangle;
import com.example.testdemo.service.impl.Square;
//工厂模式设计模式
public class ShapeFactory {

    public Shape getShape(String shapeType){
       if(shapeType==null){
           return null;
       }
       if(shapeType.equalsIgnoreCase("CIRCLE")){
           return new Circle();
       }
        if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
