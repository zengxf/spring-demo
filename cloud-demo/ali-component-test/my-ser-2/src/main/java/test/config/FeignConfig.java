package test.config;

import feign.*;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/25.
 */
@Slf4j
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
    Logger.Level feignLoggerLeave() { // 这个和 Logback 都要配置
        return Logger.Level.HEADERS;
        // return Logger.Level.NONE;
    }

    /*** 默认不进行重试 {@link FeignClientsConfiguration} */
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100L, 1000L, 2); // 重试 2 - 1 次
    }

    /**
     * 默认的错误编码器（{@link ErrorDecoder.Default}），<br/>
     * 只对有 Retry-After({@link Util#RETRY_AFTER}) 头的响应进行重试处理！<br/>
     * 相当于抛出 {@link RetryableException} 异常，后面才会重试！
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new MyFeignErrorDecoder();
    }

    public static class MyFeignErrorDecoder implements ErrorDecoder {
        @Override
        public Exception decode(String methodKey, Response response) {
            if (is4xx(response.status())) {
                log.error("请求服务出错！status: [{}], body: [{}]", response.status(), response.body());
                throw new RuntimeException("客户端错误！status: " + response.status());
            }

            FeignException exception = FeignException.errorStatus(methodKey, response);

            if (response.request().httpMethod() == Request.HttpMethod.GET) { // 只对 GET 重试
                log.info("将报错异常，进行重试！");
                return new RetryableException(
                        response.status(),
                        exception.getMessage(),
                        response.request().httpMethod(),
                        exception,
                        new Date(),
                        response.request());
            }

            return exception;
        }

        boolean is4xx(int status) {
            return 400 <= status && status <= 499;
        }
    }

}
