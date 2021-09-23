package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.AbstractApplicationTest5;

@Slf4j
public class Test5BizService extends AbstractApplicationTest5 {

    @Autowired
    private BizService service;

    @Test
    @DisplayName("测试 1")
    @Order(1)
    public void hello() {
        service.hello();
        log.info("ok!");
    }

    @Test
    @DisplayName("测试 2")
    @Order(2)
    public void hello2() {
        service.hello();
        log.info("2 ok!");
    }

}
