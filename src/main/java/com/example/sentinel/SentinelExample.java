package com.example.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.List;

public class SentinelExample {

    {
        initsentinel();
    }

    private final static String Resources = "doRequest";

    public static void main(String[] args) {
        SentinelExample sentinelExample = new SentinelExample();
        sentinelExample.doRequest();
    }

    public void doRequest() {
        while (true) {
            // 资源名可使用任意有业务语义的字符串
            if (SphO.entry(Resources)) {
                try {
                    System.out.println("执行业务逻辑");
                } finally {
                    SphO.exit();
                }
            } else {
                System.out.println("被限流");
            }
        }
    }


    private void initsentinel() {
        FlowRule rule = new FlowRule();
        rule.setCount(5);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setResource(Resources);
        List<FlowRule> list = new ArrayList<>();
        list.add(rule);
        FlowRuleManager.loadRules(list);
    }

}
