package test;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 只是声明
 */
@DubboComponentScan("test.dubboapi")
// @EnableDiscoveryClient
@SpringBootApplication
public class DSerAppAnnotation {
}
