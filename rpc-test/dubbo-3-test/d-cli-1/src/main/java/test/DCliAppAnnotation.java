package test;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 只是声明
 */
@DubboComponentScan("test.dubboconsumer")
@SpringBootApplication
public class DCliAppAnnotation {
}
