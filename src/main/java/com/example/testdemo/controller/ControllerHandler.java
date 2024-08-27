package com.example.testdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControllerHandler {
    @Autowired
    private Environment env;

    public void validateRequestParamNotNUll(Map<String,Object> map){
        List list = new ArrayList<>();
    }
}
