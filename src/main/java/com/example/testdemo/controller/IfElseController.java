package com.example.testdemo.controller;

import com.example.testdemo.factory.OrderStrategyFactory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ifelse")
@AllArgsConstructor
public class IfElseController {
    private final OrderStrategyFactory orderStrategyFactory;
    @GetMapping("strategy")
    public Boolean strategy(Integer status){
        return orderStrategyFactory.handler(status,"1");
    }
}
