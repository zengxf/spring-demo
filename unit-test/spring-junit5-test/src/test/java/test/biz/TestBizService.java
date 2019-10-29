package test.biz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import test.AbstractApplicationJUnit4;

@Slf4j
public class TestBizService extends AbstractApplicationJUnit4 {

    @Autowired
    private BizService service;

    @Test
    public void hello() {
        service.hello();
        log.info( "ok!" );
    }

}
