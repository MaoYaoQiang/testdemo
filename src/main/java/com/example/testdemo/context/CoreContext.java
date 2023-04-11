package com.example.testdemo.context;

import com.example.testdemo.framework.BizObject;

public class CoreContext {
    public static final ThreadLocal<Object> INPUT_CONTEXT=new ThreadLocal<>();


    public static <TYPE> BizObject getInput(BizObject<TYPE> type){
        Object o = INPUT_CONTEXT.get();
        return (BizObject<TYPE>)o;
    }
}
