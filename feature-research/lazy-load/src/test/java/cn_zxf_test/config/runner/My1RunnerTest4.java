package cn_zxf_test.config.runner;

import cn_zxf_test.BaseFastTester;
import cn_zxf_test.biz.BizService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class My1RunnerTest4 extends BaseFastTester {

    @Autowired
    My1Runner runner;
    @Autowired
    BizService service;
    @Value("${server.port:0}")
    Integer port;

    @Test
    public void test() {
        log.info("---------- OK ----------");
        log.info("runner: [{}]", runner);
        log.info("service: [{}]", service);
        log.info("port: [{}]", port);
    }

}