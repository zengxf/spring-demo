package cn_zxf_test.biz;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BizService {

    @Value("${app.sign}")
    String appSign;

    @PostConstruct
    public void init() {
        log.info("== BizService init! sign: [{}] ==", appSign);
    }

    public void hello() {
        log.info("== hello ==");
    }

}
