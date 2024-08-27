package com.example.testdemo.strategy;

public interface OrderStrategy {
    /**
     * 获取实现类标识
     * @return
     */
    Integer getType();

    /**
     * 逻辑处理
     * @param params
     * @return
     */
    Boolean handler(Object params);
}
