package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseSer1AppTest5;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
@Slf4j
public class SentinelTestServiceTest extends BaseSer1AppTest5 {

    @Autowired
    SentinelTestService service;

    @Test
    public void sayHello() {
        for (int i = 0; i < 10; i++) {
            String res = service.sayHello();
            log.info("i: [{}], res: [{}]", i, res);
        }
    }

}