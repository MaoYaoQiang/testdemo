package com.example.testdemo.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResult {
    private Integer state;
    private String  msg;
    private Map<String,Object> results;

    public RestResult(Integer state,String mag,String key,Object value){
        this.state=state;
        this.msg=msg;
        Map<String,Object> results=new HashMap<>();
        results.put(key,value);
        this.results=results;
    }
}
