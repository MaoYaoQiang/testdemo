package com.example.testdemo.controller;

import java.util.concurrent.*;

/**
 * @auther zgp
 * @desc
 * @date 2023/8/9
 */
public class Com {

     ThreadPoolExecutor EXECUTOR=new ThreadPoolExecutor(
            3,
            10,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(3));

    public  void getUserInfo() throws ExecutionException, InterruptedException {
        CompletableFuture<String> userInfo = getUserIngo();
        CompletableFuture<String> ltTestImagaInfo = getLtTestImagaInfo();
        CompletableFuture[] array=new CompletableFuture[2];
        array[0]=userInfo;
        array[1]=ltTestImagaInfo;
    }

    public  CompletableFuture<String> getUserIngo(){
        return CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开始异步流程");
            return "200";
        },EXECUTOR).exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            return throwable.getMessage();
        });
    }

    public  CompletableFuture<String> getLtTestImagaInfo(){
        return CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("getLtTestImagaInfo");
            return "200";
        },EXECUTOR).exceptionally(throwable -> {
            return throwable.getMessage();
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main  start");
        new Com().getUserInfo();
        System.out.println("main  over");
    }


}
