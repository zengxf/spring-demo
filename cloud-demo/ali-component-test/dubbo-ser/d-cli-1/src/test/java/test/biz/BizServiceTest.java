package test.biz;

import test.BaseDCli1AppTest5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BizServiceTest extends BaseDCli1AppTest5 {

    @Autowired
    private BizService service;

    @Test
    @DisplayName("测试 1")
    public void hello() {
        service.hello();
        log.info("ok!");
    }

}
