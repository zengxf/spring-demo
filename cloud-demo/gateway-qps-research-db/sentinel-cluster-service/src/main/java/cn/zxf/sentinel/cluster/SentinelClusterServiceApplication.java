package cn.zxf.sentinel.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Sentinel 集群流控 Token Server
 *
 * @author zxf
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelClusterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelClusterServiceApplication.class, args);
    }

}
