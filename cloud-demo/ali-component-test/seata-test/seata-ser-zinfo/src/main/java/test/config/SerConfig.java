package test.config;

// import jakarta.annotation.PostConstruct;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * <br/>
 * Created by ZXFeng on 2023/2/23.
 */
@Component
@RefreshScope
@Slf4j
public class SerConfig {

    @Value("${my.config.test}") // 配合 @RefreshScope 注解才会更新
    private String config1;
    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private ConfigUser user;
    @Autowired
    private NacosDiscoveryProperties nacos;


    @PostConstruct
    public void init() {
        log.info("\n-------------------------------\n");
        log.info("config-1: [{}]", this.config1);
        log.info("app-name: [{}]", this.appName);
        log.info("config-user: [{}]", this.user);
        log.info("config-nacos: [{}]", this.nacos);
        log.info("\n\n-------------------------------");
    }

}
