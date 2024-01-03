package test.feign;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseSer2AppTest5;


/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
@Slf4j
public class Ser1BizFeignTest extends BaseSer2AppTest5 {

    @Autowired
    Ser1BizFeign feign;

    @Test
    public void printFeign() {
        log.info("feign: [{}]", feign);
    }

    @Test
    public void hello() {
        for (int i = 0; i < 10; i++) {
            String res = feign.hello();
            log.info("i: [{}], res: [{}]", i, res);
        }
    }

}