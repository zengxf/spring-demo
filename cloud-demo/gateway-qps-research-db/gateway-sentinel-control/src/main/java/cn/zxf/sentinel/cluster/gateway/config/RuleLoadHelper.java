package cn.zxf.sentinel.cluster.gateway.config;

import cn.zxf.sentinel.cluster.config.RuleConstants;
import cn.zxf.sentinel.cluster.config.RulePO;
import cn.zxf.sentinel.cluster.config.RuleSetupUtils;
import cn.zxf.sentinel.cluster.config.RuleType;
import cn.zxf.sentinel.cluster.gateway.mapper.RuleMapper;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p/>
 * Created by ZXFeng on 2026/3/2
 */
@Component
@Slf4j
public class RuleLoadHelper implements RuleConstants {

    @Resource
    private RuleMapper ruleMapper;

    private volatile Set<String> ruleKeys;

    public void loadRules() {
        List<RulePO> rulePOS = ruleMapper.selectEnabledAll();
        log.info("加载出 {} 条规则", rulePOS.size());
        rulePOS.forEach(rule -> log.info("规则 [{}] >>> {}", rule.getId(), rule));

        List<FlowRule> rules = RuleSetupUtils.buildRules(rulePOS);
        FlowRuleManager.loadRules(rules);

        this.ruleKeys = rules.stream().map(FlowRule::getResource).collect(Collectors.toSet());
    }

    public Set<String> reloadRules() {
        this.loadRules();
        return this.ruleKeys;
    }

    /*** 按优先级查找规则 key */
    public String findRuleKey(RuleType type, String tenant, String app, String api) {
        String apiLvlKey = RuleSetupUtils.buildRuleKey(type, tenant, app, api);
        if (ruleKeys.contains(apiLvlKey)) {
            return apiLvlKey;
        }

        String appLvlKey = RuleSetupUtils.buildRuleKey(type, tenant, app, null);
        if (ruleKeys.contains(appLvlKey)) {
            return appLvlKey;
        }

        String tenantLvlKey = RuleSetupUtils.buildRuleKey(type, tenant, null, null);
        if (ruleKeys.contains(tenantLvlKey)) {
            return tenantLvlKey;
        }

        return NONE_RULE_SIGN;
    }

}
