package com.example.testdemo.strategy;

import org.springframework.stereotype.Service;

@Service
public class SignOrderStrategy implements OrderStrategy {
    @Override
    public Integer getType() {
        return 2;
    }

    @Override
    public Boolean handler(Object params) {
        System.out.println("222222");
        return null;
    }
}
