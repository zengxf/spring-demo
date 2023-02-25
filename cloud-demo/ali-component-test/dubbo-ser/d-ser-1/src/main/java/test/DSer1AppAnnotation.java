package test;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 只是声明
 */
@DubboComponentScan("test.dubboapi")
@EnableDiscoveryClient
@SpringBootApplication
public class DSer1AppAnnotation {
}
