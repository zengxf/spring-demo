package cn.zxf.sentinel.cluster.config;

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
        // 配置 Token Server 传输配置
        ServerTransportConfig transportConfig = new ServerTransportConfig();
        transportConfig.setPort(serverPort);
        transportConfig.setIdleSeconds(600);
        ClusterServerConfigManager.loadGlobalTransportConfig(transportConfig);

        // 配置命名空间（支持多个应用）
        Set<String> namespaceSet = Collections.singleton(applicationName);
        ClusterServerConfigManager.loadServerNamespaceSet(namespaceSet);

        log.info("Sentinel Cluster Token Server 初始化完成，端口: {}", serverPort);
    }

}
