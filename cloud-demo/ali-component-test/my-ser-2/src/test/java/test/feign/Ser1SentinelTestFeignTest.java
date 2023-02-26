package test.feign;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseSer2AppTest5;

import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/26.
 */
@Slf4j
class Ser1SentinelTestFeignTest extends BaseSer2AppTest5 {

    @Autowired
    Ser1SentinelTestFeign feign;

    @Test
    void sentinelTest() {
        for (int i = 0; i < 10; i++) {
            String res = feign.sentinelTest();
            log.info("i: [{}], res: [{}]", i, res);
        }
    }

    @Test
    void getArray() {
        List<String> list = feign.getArray();
        list.forEach(str -> log.info("str: [{}]", str));
    }
}