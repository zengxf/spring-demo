package cn.test.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cn.test.BaseTester5;

@Slf4j
public class Test5BizService extends BaseTester5 {

    @Autowired
    private BizService service;

    @Test
    @DisplayName("测试 1")
    public void hello() {
        service.hello();
        log.info("ok!");
    }

}
