package cn.zxf.sentinel.cluster.config;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
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

    @Value("${spring.application.name}")
    private String applicationName;

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
            Set<String> namespaceSet = Collections.singleton(applicationName);
            ClusterServerConfigManager.loadServerNamespaceSet(namespaceSet);

            // // 4. 启动 Token Server（关键步骤！）
            // ClusterTokenServer.start();

            log.info("Sentinel Cluster Token Server 启动成功，端口: {}, 命名空间: {}",
                    serverPort, applicationName);
        } catch (Exception e) {
            log.error("Sentinel Cluster Token Server 启动失败", e);
            throw new RuntimeException("Sentinel Cluster Token Server 启动失败", e);
        }
    }

}
