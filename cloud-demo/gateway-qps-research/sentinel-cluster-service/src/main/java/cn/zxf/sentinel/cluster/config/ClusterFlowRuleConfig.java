package cn.zxf.sentinel.cluster.config;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.ClusterFlowConfig;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 集群流控规则配置
 *
 * @author zxf
 */
@Slf4j
@Configuration
public class ClusterFlowRuleConfig {

    @Value("${spring.cloud.sentinel.cluster.namespace:ns_test}")
    private String namespaceName;

    @PostConstruct
    public void initClusterFlowRules() {
        List<FlowRule> rules = new ArrayList<>();

        // 为 test-web1 配置集群流控规则，QPS 限制为 10
        FlowRule rule = new FlowRule();
        rule.setResource("test-web1");  // 资源名称
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);  // 限流阈值类型：QPS
        int qps = 3;
        rule.setCount(qps);  // QPS 阈值为 3
        rule.setClusterMode(true);  // 开启集群模式

        // 配置集群流控相关参数
        ClusterFlowConfig clusterConfig = new ClusterFlowConfig();
        clusterConfig.setFlowId(20260209L);  // 全局唯一的规则 ID
        rule.setClusterConfig(clusterConfig);

        rules.add(rule);

        // 加载集群流控规则
        ClusterFlowRuleManager.loadRules(namespaceName, rules);

        log.info("集群流控规则初始化完成，test-web1 QPS 限制为 {}", qps);
    }

}
