package cn_zxf_test.biz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import cn_zxf_test.BaseNotLazyTester;

@Slf4j
public class BizServiceNotLazyTest extends BaseNotLazyTester {

    @Autowired
    private BizService service;

    @Test
    public void hello() {
        service.hello();
        log.info( "ok!" );
    }

}
