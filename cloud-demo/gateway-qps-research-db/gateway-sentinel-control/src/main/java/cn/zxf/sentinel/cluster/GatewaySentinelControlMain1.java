package cn.zxf.sentinel.cluster;

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
        SpringApplication.run(GatewaySentinelControlMain1.class, args);
    }

}
