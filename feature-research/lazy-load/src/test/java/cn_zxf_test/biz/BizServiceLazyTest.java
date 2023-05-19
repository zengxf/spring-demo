package cn_zxf_test.biz;

import cn_zxf_test.BaseLazyTester;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BizServiceLazyTest extends BaseLazyTester {

    @Autowired
    private BizService service;

    @Test
    public void hello() {
        service.hello();
        log.info("ok!");
    }

}
