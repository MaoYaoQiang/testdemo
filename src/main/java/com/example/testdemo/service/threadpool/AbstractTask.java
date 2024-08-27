package com.example.testdemo.service.threadpool;

import com.example.testdemo.model.result.ResultDto;

import java.util.concurrent.Callable;

public abstract class AbstractTask implements Callable<ResultDto> {
}
