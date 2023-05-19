package cn_zxf_test.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class My2Service {

    @PostConstruct
    public void init() throws InterruptedException {
        log.info("== My 2222 service init! ==");
        Thread.sleep(1000L);
    }

    public void hello() {
        log.info("== hello ==");
    }

}
