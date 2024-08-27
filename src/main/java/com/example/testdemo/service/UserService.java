package com.example.testdemo.service;

import com.example.testdemo.mapper.LtTestImagaMapper;
import com.example.testdemo.mapper.UserMapper;
import com.example.testdemo.model.dto.LtTestImaga;
import com.example.testdemo.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Service
public class UserService {
    //获取用户列表
    @Autowired
    UserMapper userMapper;
    @Autowired
    LtTestImagaMapper ltTestImagaMapper;

    private static final ThreadPoolExecutor EXECUTOR=new ThreadPoolExecutor(
            3,
            10,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(3));

    public void getUserInfo() throws ExecutionException, InterruptedException {

        CompletableFuture<String> userInfo = getUserIngo();
        CompletableFuture<String> ltTestImagaInfo = getLtTestImagaInfo();
        CompletableFuture[] array=new CompletableFuture[2];
        Thread.sleep(5000);
        array[0]=userInfo;
        array[1]=ltTestImagaInfo;
//        for (CompletableFuture completableFuture : array) {
//            Object o = completableFuture.get();
//            System.out.println(o.toString());
//        }
    }

    public  CompletableFuture<String> getUserIngo(){
       return CompletableFuture.supplyAsync(()->{
           System.out.println("守护线程：" + Thread.currentThread().isDaemon());
           System.out.println("守护线程：" + Thread.currentThread().getName());
           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               System.out.println(e.getMessage());
               e.printStackTrace();
           }
           System.out.println("开始异步流程");
            List<LtTestImaga> ltTestImaga = ltTestImagaMapper.getLtTestImaga();
           System.out.println(Thread.currentThread().getName()+"异步日志打印测试1");
           return "200";
        },EXECUTOR).exceptionally(throwable -> {
           System.out.println(throwable.getMessage());
           return throwable.getMessage();
        });
    }

    public   CompletableFuture<String> getLtTestImagaInfo(){
        return CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<User> users = userMapper.selectUserInfo();
            System.out.println(Thread.currentThread().getName()+"异步日志打印测试2");
            return "200";
        },EXECUTOR).exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            return throwable.getMessage();
        });
    }

}
