package test.biz;

import test.AbstractApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestBizService extends AbstractApplicationTest {

    @Autowired
    private BizService service;

    @Test
    public void hello() {
        service.hello();
        log.info("ok!");
    }

}
