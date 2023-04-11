package com.example.testdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TestdemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestdemoApplication.class, args);
       String S="1234567890";
        String substring = S.substring(0, S.length() - 4);
        System.out.println(substring+"****");
    }

}
