package test.biz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import error_order.biz.BizService;
import lombok.extern.slf4j.Slf4j;
import test.AbstractApplicationTest;

@Slf4j
public class TestBizService extends AbstractApplicationTest {

    @Autowired
    private BizService service;

    @Test
    public void hello() {
        service.hello();
        log.info( "ok!" );
    }

}
