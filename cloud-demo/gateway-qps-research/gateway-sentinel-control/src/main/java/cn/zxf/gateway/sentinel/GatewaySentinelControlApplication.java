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
public class GatewaySentinelControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewaySentinelControlApplication.class, args);
    }
}
