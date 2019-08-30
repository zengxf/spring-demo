package cn.zxf.spring_research.biz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zxf.spring_research.AbstractApplicationTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestBiz2Service extends AbstractApplicationTest {

    @Autowired
    private Biz2Service service;

    @Test
    public void hello() {
        service.hello( "" );
        log.info( "ok!" );
    }

    @Test
    public void helloDto() {
        BizDto dto = new BizDto();
        service.hello( dto );
        log.info( "ok!" );
    }

}
