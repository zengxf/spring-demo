package test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 只是声明
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Ser2AppAnnotation {
}
