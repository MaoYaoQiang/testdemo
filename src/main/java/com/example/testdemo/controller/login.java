package com.example.testdemo.controller;

import com.example.testdemo.model.vo.LoginVo;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class login {
    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginVo user, BindingResult result) {
        System.out.println(result.toString());
        if(result.hasErrors()){
            Map<String,String> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(item->{
                String field = item.getField();
                String defaultMessage = item.getDefaultMessage();
                map.put(field,defaultMessage);
            });
            return map.toString();
        }
        return "你好,世界！";

    }

}
