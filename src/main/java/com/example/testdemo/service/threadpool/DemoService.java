package com.example.testdemo.service.threadpool;

import com.example.testdemo.controller.designpattern.DemoTask;
import com.example.testdemo.model.result.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Service
public class DemoService {
    @Autowired
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    public List<String> useTaskPoolDemo() throws ExecutionException, InterruptedException {
        List<String> strings = Arrays.asList("Spider man", "Haoke", "Mary", "Lilei", "Han Meimei");
        long start = System.currentTimeMillis();
        List<String> resultList = new ArrayList<>();
        List<Future<ResultDto>> futureList = new ArrayList<>();
        for (String string : strings) {
            DemoTask demoTask = new DemoTask(string);
            Future<ResultDto> result = this.taskExecutor.submit(demoTask);
            futureList.add(result);
            log.info("##" + string +"## 添加到结果： " + string + "结果时间： " + LocalDateTime.now());
        }
        // future.get() 会阻塞调用线程（主线程），如果在上面的循环中获取，整个服务就会变成并行，失去使用线程池的意义
        for (Future<ResultDto> resultFuture : futureList) {
            ResultDto data = resultFuture.get();
            log.info("## 获取到future结果： " + data.getMsg().toString() + "结果时间： " + LocalDateTime.now());
            resultList.add(data.getMsg().toString());
        }
        long end = System.currentTimeMillis();
        log.info("程序执行时间：" + String.valueOf(end - start));
        return resultList;
    }
}
