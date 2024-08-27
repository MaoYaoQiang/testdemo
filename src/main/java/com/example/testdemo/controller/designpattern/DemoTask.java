package com.example.testdemo.controller.designpattern;

import com.example.testdemo.model.result.ResultDto;
import com.example.testdemo.service.threadpool.AbstractTask;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class DemoTask  extends AbstractTask {

    private String name;
    @Override
    public ResultDto call() throws Exception {
        ResultDto resultDto = new ResultDto();
        log.info(Thread.currentThread().getName()+"接收到任务"+name+"当前时间"+ LocalDateTime.now().toString());
        resultDto.setMsg("CALL"+name);
        TimeUnit.SECONDS.sleep(2);
        return resultDto;
    }
}
