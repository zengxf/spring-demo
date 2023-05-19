package cn_zxf_test.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class My3Service {

    @PostConstruct
    public void init() throws InterruptedException {
        Thread.sleep(1000L);
        log.info("== My 3333 service init! ==");
    }

    public void hello() {
        log.info("== hello ==");
    }

}
