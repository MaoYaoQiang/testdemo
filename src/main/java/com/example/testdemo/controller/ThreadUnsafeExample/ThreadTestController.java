package com.example.testdemo.controller.ThreadUnsafeExample;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTestController {
    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
                System.out.println(countDownLatch.getCount());
            });
        }
        System.out.println(countDownLatch.getCount());
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }
}
