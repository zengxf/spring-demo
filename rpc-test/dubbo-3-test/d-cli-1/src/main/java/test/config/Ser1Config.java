package test.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/23.
 */
@Component
@Slf4j
public class Ser1Config {

    @Value("${my.config.test}") // 配合 @RefreshScope 注解才会更新
    @Getter
    private String config1;
    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private ConfigUser user;


    @PostConstruct
    public void init() {
        log.info("-------------------------------\n\n");
        log.info("config-1: [{}]", this.config1);
        log.info("app-name: [{}]", this.appName);
        log.info("config-user: [{}]", this.user);
        log.info("\n\n-------------------------------");
    }

}
