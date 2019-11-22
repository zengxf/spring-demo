package test_import_selector.biz;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import test_import_selector.AbstractApplicationTest;
import test_import_selector.biz.HelloService;

@Slf4j
public class TestBizService extends AbstractApplicationTest {

    @Autowired
    private List<HelloService> helloServices;

    @Test
    public void hello() {
        this.helloServices.forEach( HelloService::doSomething );
        log.info( "ok!" );
    }

}
