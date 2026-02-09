package cn.zxf.sentinel.cluster.config;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.ClusterRuleConstant;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.ClusterFlowConfig;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * Created by ZXFeng on 2026/2/9
 */
@Slf4j
@Configuration
@DependsOn("sentinelClusterConfig") // 对 @PostConstruct 顺序执行，这个才有效果
public class ClusterFlowRuleConfig {

    @Value("${spring.cloud.sentinel.cluster.namespace:ns_test}")
    private String namespaceName;

    // 集群流控规则配置
    @PostConstruct
    public void initClusterFlowRules() {
        initClusterFlowRules(namespaceName);
    }

    public static void initClusterFlowRules(String namespaceName) {
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
        // 关键设置：将阈值模式改为全局模式
        // 0 代表单机均摊 (默认), 1 代表集群总体
        clusterConfig.setThresholdType(ClusterRuleConstant.FLOW_THRESHOLD_GLOBAL);

        rule.setClusterConfig(clusterConfig);

        rules.add(rule);

        // 加载集群流控规则
        // 要在授权 Namespace 之后
        ClusterFlowRuleManager.loadRules(namespaceName, rules);

        log.info("集群流控规则初始化完成，test-web1 QPS 限制为 {}", qps);
    }

}
