package test.config;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
import org.springframework.cloud.loadbalancer.blocking.client.BlockingLoadBalancerClient;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/25.
 */
@Deprecated(since = "升级 Ali 的版本即可去掉 Ribbon 依赖")
// @Configuration
public class FeignConfig {

    @Bean
    public LoadBalancerClient loadBalancerClient(
            LoadBalancerClientFactory factory, LoadBalancerProperties properties
    ) {
        return new BlockingLoadBalancerClient(factory, properties);
    }

}
