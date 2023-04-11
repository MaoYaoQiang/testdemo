package com.example.testdemo.framework;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class BizObject<DATA> implements Serializable {
    /**
     * 具体的业务对象
     */
    private DATA data;
    /**
     * bizTag
     */
    private String bizTag;
}
