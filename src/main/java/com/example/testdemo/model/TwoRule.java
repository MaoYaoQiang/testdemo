package com.example.testdemo.model;

import com.example.testdemo.model.enuma.TestItemIdEnum;
import com.example.testdemo.model.enuma.TestItemNameEnum;
import org.jeasy.rules.annotation.*;
import org.jeasy.rules.mvel.MVELRule;

import java.util.Map;

@Rule(name="被8整除",description = "判断是否被8整除")
public class TwoRule {
    @Condition
    public boolean isEin(@Fact("map") Map<String,String> map){
        String id = map.get("id");
        String name1 = map.get("name");
        String value = TestItemNameEnum.valueOf("ITEM_ID_" + "HCV_RNA").getValue();
        String name2 = TestItemNameEnum.valueOf("ITEM_ID_" + "HCV_RNA").getName();
        return  (id.contains(value) && !name1.equals(name2));
    }
    @Action
    public void action(){
        System.out.println("发送HCV-RNA定量短息给医生");
    }

    @Priority
    public int getPro(){
        return 2;
    }

}
