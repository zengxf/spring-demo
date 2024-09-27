package cn_zxf_test.biz;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BizService {

    @Value("${test.name}")
    String testName;

    @PostConstruct
    public void init() {
        log.info("== BizService init! ==");
        log.info("app-sign: [{}]", testName);
    }

    public void hello() {
        log.info("== hello ==");
    }

}
