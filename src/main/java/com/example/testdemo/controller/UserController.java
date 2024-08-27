package com.example.testdemo.controller;

import com.example.testdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/getUserInfo")
    public void testCompletableFuture() throws ExecutionException, InterruptedException {
        userService.getUserInfo();
        System.out.println("主线程"+Thread.currentThread().getName());
    }
}
