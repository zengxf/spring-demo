package cn.zxf.sentinel.cluster.config;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.cluster.server.EmbeddedClusterTokenServerProvider;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;
import com.alibaba.csp.sentinel.slots.block.ClusterRuleConstant;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.ClusterFlowConfig;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Sentinel 集群流控配置
 *
 * @author zxf
 */
@Slf4j
@Configuration
public class SentinelClusterConfig {

    @Value("${spring.cloud.sentinel.cluster.server.port:18730}")
    private Integer serverPort;

    @Value("${spring.cloud.sentinel.cluster.namespace:ns_test}")
    private String namespaceName;

    @PostConstruct
    public void init() {
        try {
            log.info("开始初始化 Sentinel Cluster Token Server...");

            // 1. 设置为集群服务端模式
            ClusterStateManager.applyState(ClusterStateManager.CLUSTER_SERVER);

            // 2. 配置 Token Server 传输配置
            ServerTransportConfig transportConfig = new ServerTransportConfig();
            transportConfig.setPort(serverPort);
            transportConfig.setIdleSeconds(600);
            ClusterServerConfigManager.loadGlobalTransportConfig(transportConfig);

            // 3. 配置命名空间（支持多个应用）
            Set<String> namespaceSet = Collections.singleton(namespaceName);
            ClusterServerConfigManager.loadServerNamespaceSet(namespaceSet);

            // 4. 启动嵌入式 Token Server（使用默认实现）
            EmbeddedClusterTokenServerProvider.getServer().start();

            log.info("Sentinel Cluster Token Server 启动成功，端口: {}, 命名空间: {}", serverPort, namespaceName);

            this.initClusterFlowRules();
        } catch (Exception e) {
            log.error("Sentinel Cluster Token Server 启动失败", e);
            throw new RuntimeException("Sentinel Cluster Token Server 启动失败", e);
        }
    }

    // 集群流控规则配置
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
        // 关键设置：将阈值模式改为全局模式
        // 0 代表单机均摊 (默认), 1 代表集群总体
        clusterConfig.setThresholdType(ClusterRuleConstant.FLOW_THRESHOLD_GLOBAL);

        rule.setClusterConfig(clusterConfig);

        rules.add(rule);

        // 加载集群流控规则
        ClusterFlowRuleManager.loadRules(namespaceName, rules);

        log.info("集群流控规则初始化完成，test-web1 QPS 限制为 {}", qps);
    }

}
