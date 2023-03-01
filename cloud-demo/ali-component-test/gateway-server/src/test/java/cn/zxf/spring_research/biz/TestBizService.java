package cn.zxf.spring_research.biz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zxf.spring_research.BaseGatewayAppTest5;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestBizService extends BaseGatewayAppTest5 {

    @Autowired
    private BizService service;

    @Test
    public void hello() {
        service.hello();
        log.info("ok!");
    }

}
