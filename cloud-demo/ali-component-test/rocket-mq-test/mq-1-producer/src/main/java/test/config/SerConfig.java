package test.config;

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
public class SerConfig {

    @Value("${my.config.sign}") // 配合 @RefreshScope 注解才会更新
    private String sign;
    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private ConfigUser user;


    @PostConstruct
    public void init() {
        log.info("\n-------------------------------\n");
        log.info("app-sign: [{}]", this.sign);
        log.info("app-name: [{}]", this.appName);
        log.info("config-user: [{}]", this.user);
        log.info("\n\n-------------------------------");
    }

}
