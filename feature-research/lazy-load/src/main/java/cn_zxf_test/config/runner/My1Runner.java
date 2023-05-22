package cn_zxf_test.config.runner;

import cn_zxf_test.biz.BizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * <br>
 * Created by ZXFeng on 2023/5/22
 */
@Component
@Slf4j
public class My1Runner implements ApplicationRunner {

    @Autowired
    BizService service;

    @Override
    public void run(ApplicationArguments args) {
        log.info("进入 run ...");
        log.info("service: [{}]", service);
    }

}
