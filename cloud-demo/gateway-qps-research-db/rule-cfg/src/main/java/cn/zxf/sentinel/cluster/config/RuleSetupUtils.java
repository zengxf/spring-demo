package cn.zxf.sentinel.cluster.config;

import com.alibaba.csp.sentinel.slots.block.ClusterRuleConstant;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.ClusterFlowConfig;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p/>
 * Created by ZXFeng on 2026/2/9
 */
@Slf4j
public class RuleSetupUtils implements RuleConstants {

    public static List<FlowRule> buildRules(List<RulePO> rulePOS) {
        List<FlowRule> rules = new ArrayList<>();
        if (rulePOS == null || rulePOS.isEmpty()) {
            return rules;
        }

        rulePOS.stream()
                .map(po -> {
                    AssertUtil.notNull(po.getId(), "Id is null.");
                    AssertUtil.notNull(po.getTenant(), "Tenant is null.");
                    AssertUtil.notNull(po.getLimit(), "Limit is null.");
                    AssertUtil.notNull(po.getWindow(), "Window is null.");

                    RuleType ruleType = RuleType.of(po.getType());
                    String resource = buildRuleKey(ruleType, po.getTenant(), po.getApp(), po.getApi());

                    FlowRule rule = buildQpsRule(resource, po.getLimit(), po.getId(), po.getWindow());
                    log.info("集群流控规则初始化 >>> {} 限制为 {}/{} ms", resource, po.getLimit(), po.getWindow());

                    return rule;
                })
                .forEach(rules::add);

        return rules;
    }

    public static String buildRuleKey(RuleType type, String tenant, String app, String api) {
        if (type == null || tenant == null) {
            return null;
        }

        app = Optional.ofNullable(app).filter(StringUtil::isNotBlank).orElse(DEFAULT_APP);
        api = Optional.ofNullable(api).filter(StringUtil::isNotBlank).orElse(DEFAULT_API);
        String suffix = type.suffix;

        String resource = String.format(SOURCE_FMT, tenant, app, api, suffix);
        log.debug("Build resource: [{}]", resource);

        return resource;
    }

    public static FlowRule buildQpsRule(String resource, double qps, long id, int windowMs) {
        FlowRule rule = new FlowRule();
        rule.setResource(resource);  // 资源名称
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(qps);
        rule.setClusterMode(true);

        ClusterFlowConfig cfg = buildFlowCfg(id, windowMs);
        rule.setClusterConfig(cfg);

        return rule;
    }

    public static ClusterFlowConfig buildFlowCfg(long id, int windowMs) {
        ClusterFlowConfig cfg = new ClusterFlowConfig();
        cfg.setFlowId(id);
        cfg.setThresholdType(ClusterRuleConstant.FLOW_THRESHOLD_GLOBAL);
        cfg.setWindowIntervalMs(windowMs);
        return cfg;
    }

    public static boolean isNotEnabled(String key) {
        return Objects.equals(NONE_RULE_SIGN, key);
    }

}
