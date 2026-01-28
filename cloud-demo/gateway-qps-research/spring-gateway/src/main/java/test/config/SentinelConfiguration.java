package test.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * Created by ZXFeng on 2026/1/28
 */
@Configuration
@Slf4j
public class SentinelConfiguration {

    @PostConstruct
    public void initGatewayRules() {
        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();
        rule.setResource("test-web1");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); // 设置为 QPS 模式
        rule.setCount(3); // 阈值为 3
        rules.add(rule);

        FlowRuleManager.loadRules(rules);

        log.info("==== 初始化 {} 条规则", rules.size());
    }

}
