package test;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 只是声明
 */
@DubboComponentScan("test.dubboconsumer")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class DCli1AppAnnotation {
}
