package cn.zxf.spring_research.biz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.zxf.spring_research.AbstractApplicationTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestWsWithSpring extends AbstractApplicationTest {

    @Autowired
    @Qualifier( "helloClient" )
    HelloService service;

    @Test
    public void test() {
        log.info( "test-say: {}", service.sayHello() );
        log.info( "test-foo: {}", service.foo()
                .getName() );
    }

}
