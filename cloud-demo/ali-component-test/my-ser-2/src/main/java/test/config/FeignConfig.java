package test.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/25.
 */
@Configuration
public class FeignConfig {

    // @Deprecated(since = "升级 Ali 的版本即可去掉 Ribbon 依赖")
    // @Bean
    // public LoadBalancerClient loadBalancerClient(
    //         LoadBalancerClientFactory factory, LoadBalancerProperties properties
    // ) {
    //     return new BlockingLoadBalancerClient(factory, properties);
    // }

    @Bean
    Logger.Level feignLoggerLeave() { // 这个和 logback 都要配置
        return Logger.Level.HEADERS;
        // return Logger.Level.NONE;
    }

}
