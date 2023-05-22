package cn_zxf_test.config.runner;

import cn_zxf_test.BaseFastTester5;
import cn_zxf_test.biz.BizService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class My1RunnerTest5 extends BaseFastTester5 {

    @Autowired
    My1Runner runner;
    @Autowired
    BizService service;

    @Test
    public void test() {
        log.info("---------- OK ----------");
        log.info("runner: [{}]", runner);
        log.info("service: [{}]", service);
    }

}