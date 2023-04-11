package com.example.testdemo;

import com.example.testdemo.controller.designpattern.ShapeFactory;
import com.example.testdemo.service.Shape;
import com.sun.xml.internal.fastinfoset.util.ValueArrayResourceException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class TestdemoApplicationTests {

    @Test
    void contextLoads() {
    }
    //工厂模式
   @Test
  public void factoryTest(){
//       ShapeFactory shapeFactory = new ShapeFactory();
//       Shape cricle = shapeFactory.getShape("CIRCLE");
//       cricle.draw();
       String mobile = "111111";//移动手机号码
       String mobileRegEx = "^1[3,4,5,6,7,8,9][0-9]{9}$";//正则表达式

       Pattern pattern = Pattern.compile(mobileRegEx);//函数语法 匹配的正则表达式
       Matcher matcher = pattern.matcher(mobile);//进行匹配

       if (!matcher.matches()) {//校验手机号格式是否正确，若是匹配成功则返回true
           System.out.println("移动手机号格式错误");
       }else{
           System.out.println("移动手机格式正确");
       }

   }
}
