package cn.zxf.gateway.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Gateway Sentinel Control Application
 * Sentinel 集群流控客户端网关
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewaySentinelControlMain1 {

    public static void main(String[] args) {
        // System.setProperty("csp.sentinel.log.level", "DEBUG");
        // System.setProperty("project.name", "ns_test");
        System.setProperty("spring.profiles.active", "port1");
        SpringApplication.run(GatewaySentinelControlMain1.class, args);
    }
}
