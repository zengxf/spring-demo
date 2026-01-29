package cn.zxf.gateway.sentinel.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 流控规则配置
 * 配置针对上游服务的流控规则
 */
@Slf4j
@Configuration
public class FlowRuleConfig {

    @PostConstruct
    public void initFlowRules() {
        log.info("初始化流控规则");

        List<FlowRule> rules = new ArrayList<>();

        // 为 spring-web-1 服务配置流控规则
        FlowRule web1Rule = new FlowRule();
        web1Rule.setResource("spring-web-1");
        web1Rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        web1Rule.setCount(10); // QPS 限制为 10
        web1Rule.setClusterMode(true); // 启用集群模式
        rules.add(web1Rule);

        // 为 spring-web-2 服务配置流控规则
        FlowRule web2Rule = new FlowRule();
        web2Rule.setResource("spring-web-2");
        web2Rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        web2Rule.setCount(20); // QPS 限制为 20
        web2Rule.setClusterMode(true); // 启用集群模式
        rules.add(web2Rule);

        // 加载流控规则
        FlowRuleManager.loadRules(rules);

        log.info("流控规则初始化完成,共 {} 条规则", rules.size());
    }
}
