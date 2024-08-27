package com.example.testdemo.strategy;

import org.springframework.stereotype.Service;

@Service
public class SendOrderStrategy implements OrderStrategy{
    @Override
    public Integer getType() {
        return 1;
    }

    @Override
    public Boolean handler(Object params) {
        System.out.println("1111111");
        return null;
    }
}
