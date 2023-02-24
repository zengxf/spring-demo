package test.config;

// import jakarta.annotation.PostConstruct;
import javax.annotation.PostConstruct;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.sentinel.SentinelProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;



/**
 * <br/>
 * Created by ZXFeng on 2023/2/23.
 */
@Component
@RefreshScope
@Slf4j
public class MyConfig {

    @Value("${my.config.test}") // 配合 @RefreshScope 注解才会更新
    @Getter
    private String config1;
    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.cloud.sentinel.transport.dashboard}")
    private String sentinelDashboard;
    @Autowired
    private ConfigUser user;
    @Autowired
    private NacosDiscoveryProperties nacos;
    @Autowired
    private SentinelProperties sentinel;


    @PostConstruct
    public void init() {
        log.info("-------------------------------\n\n");
        log.info("config-1: [{}]", this.config1);
        log.info("app-name: [{}]", this.appName);
        log.info("config-user: [{}]", this.user);
        log.info("config-nacos: [{}]", this.nacos);
        log.info("config-sentinel: [{}]", this.sentinel);
        log.info("config-sentinel-dashboard: [{}]", this.sentinel.getTransport().getDashboard());
        log.info("sentinel-dashboard: [{}]", this.sentinelDashboard);
        log.info("\n\n-------------------------------");
    }

}
