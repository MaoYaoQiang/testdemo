package com.example.testdemo.factory;

import com.example.testdemo.strategy.OrderStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class OrderStrategyFactory {
    private final List<OrderStrategy> orderStrategyList;

    private static Map<Integer,OrderStrategy> strategyMap = new HashMap<>();

    @PostConstruct
    private void init(){
        for (OrderStrategy orderStrategy : orderStrategyList) {
            strategyMap.put(orderStrategy.getType(),orderStrategy);
        }
    }

    /**
     * 执行方法
     * @param status
     * @param params
     * @return
     */
    public Boolean handler(Integer status,Object params){
        return strategyMap.get(status).handler(params);
    }
}
