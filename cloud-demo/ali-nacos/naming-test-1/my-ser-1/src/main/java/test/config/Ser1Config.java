package test.config;

import jakarta.annotation.PostConstruct;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
@Component
@RefreshScope
@Slf4j
public class Ser1Config {

    @Value("${my.config.test}") // 配合 @RefreshScope 注解才会更新
    @Getter
    private String config1;
    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private ConfigUser user;
    @Autowired
    private NacosDiscoveryProperties nacos;


    @PostConstruct
    public void init() {
        log.info("-------------------------------\n\n");
        log.info("config-1: [{}]", this.config1);
        log.info("app-name: [{}]", this.appName);
        log.info("config-user: [{}]", this.user);
        log.info("config-nacos: [{}]", this.nacos);
        log.info("\n\n-------------------------------");
    }

}
