package cn.zxf.sentinel.cluster.gateway.config;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.config.SentinelConfig;
import com.alibaba.csp.sentinel.util.AppNameUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Sentinel 集群客户端配置
 * 配置网关作为 Sentinel 集群流控的客户端
 */
@Slf4j
@Configuration
public class SentinelClusterClientConfig {

    @Value("${sentinel.cluster.client.server-host:localhost}")
    private String serverHost;

    @Value("${sentinel.cluster.client.server-port:18730}")
    private Integer serverPort;

    @Value("${sentinel.cluster.client.request-timeout:2000}")
    private Integer requestTimeout;

    @Value("${spring.cloud.sentinel.cluster.namespace:ns_test}")
    private String namespaceName;

    @Resource
    private RuleLoadHelper ruleLoadHelper;

    @PostConstruct
    public void init() {
        log.info("初始化 Sentinel 集群客户端配置");
        System.setProperty(SentinelConfig.PROJECT_NAME_PROP_KEY, namespaceName);
        log.info("AppNameUtil.getAppName(): [{}]", AppNameUtil.getAppName());

        // 设置为集群客户端模式
        ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT);

        // 配置集群服务端地址
        ClusterClientAssignConfig assignConfig = new ClusterClientAssignConfig();
        assignConfig.setServerHost(serverHost);
        assignConfig.setServerPort(serverPort);
        ClusterClientConfigManager.applyNewAssignConfig(assignConfig);

        // 配置客户端参数
        ClusterClientConfig clientConfig = new ClusterClientConfig();
        clientConfig.setRequestTimeout(requestTimeout);
        ClusterClientConfigManager.applyNewConfig(clientConfig);

        log.info("Sentinel 集群客户端配置完成: serverHost={}, serverPort={}, requestTimeout={}",
                serverHost, serverPort, requestTimeout);

        ruleLoadHelper.loadRules();
    }

}
