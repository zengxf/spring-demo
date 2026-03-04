package cn.zxf.sentinel.cluster.config;

import cn.zxf.sentinel.cluster.mapper.RuleMapper;
import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p/>
 * Created by ZXFeng on 2026/3/2
 */
@Component
@Slf4j
public class RuleLoadHelper {

    @Value("${spring.cloud.sentinel.cluster.namespace:ns_test}")
    private String namespaceName;

    @Resource
    private RuleMapper ruleMapper;

    public void loadRules() {
        List<RulePO> rulePOS = ruleMapper.selectEnabledAll();
        log.info("加载出 {} 条规则", rulePOS.size());
        rulePOS.forEach(rule -> log.info("规则 [{}] >>> {}", rule.getId(), rule));

        List<FlowRule> rules = RuleSetupUtils.buildRules(rulePOS);
        ClusterFlowRuleManager.loadRules(namespaceName, rules);
    }

    public void reloadRules() {
        this.loadRules();
    }

}
