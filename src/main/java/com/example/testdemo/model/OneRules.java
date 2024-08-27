package com.example.testdemo.model;

import com.example.testdemo.model.enuma.TestItemIdEnum;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;

import java.util.HashMap;
import java.util.Map;

@Rule(name="阳性判别",description = "如果是阳性的话发送短信给医生")
public class OneRules {
    @Condition

    public boolean isThree(@Fact("map") Map<String,String> map){
        System.out.println("======"+TestItemIdEnum.valueOf("ITEM_ID_"+map.get("id")));
        System.out.println(TestItemIdEnum.valueOf("ITEM_ID_"+map.get("id")).getName().equals(map.get("name")));
        return TestItemIdEnum.valueOf("ITEM_ID_"+map.get("id")).getName().equals(map.get("name"));
    }

    @Action
    public void action(){
        System.out.println("执行action动作，发送信息给医生");
    }


    public int getPriority(){
        return 1;
    }

    public static void main(String[] args) {
        RulesEngineParameters rulesEngineParameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        DefaultRulesEngine defaultRulesEngine = new DefaultRulesEngine(rulesEngineParameters);
        Rules rules = new Rules();
        //rules.register(new OneRules());
        rules.register(new TwoRule());
        Facts entries = new Facts();
        String id="111";
        String name="阳性";
        Map map = new HashMap<>();
        map.put("id","1HCV_RNA2");
        map.put("name","阳性");
        entries.put("map", map);
        defaultRulesEngine.fire(rules,entries);
    }

}
