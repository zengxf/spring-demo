package test.biz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import test.AbstractAppTest;

@Slf4j
public class BizServiceTest extends AbstractAppTest {

    @Autowired
    private BizService service;

    @Test
    public void hello() {
        service.hello();
        log.info( "ok!" );
    }

}
