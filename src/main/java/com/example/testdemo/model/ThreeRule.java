package com.example.testdemo.model;

import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;
import org.jeasy.rules.support.UnitRuleGroup;

@Rule(name="被3和8同时整除",description = "这是一个组合1 和 2引擎")
public class ThreeRule extends UnitRuleGroup {

    public void unionRule(Object... rules){
        for (Object rule : rules) {
            addRule(rule);
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

}
