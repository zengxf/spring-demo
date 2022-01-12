package test.redis;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.AbstractApplicationTest5;

/**
 * <br/>
 * Created by ZXFeng on 2022/1/12.
 */
@Slf4j
public class RateLimitServiceTest extends AbstractApplicationTest5 {

    @Autowired
    RateLimitService service;

    @Test
    @SneakyThrows
    public void test() {
        LimiterInfo info = LimiterInfo.builder()
                .type("test") // default
                .key("zxf_test")
                .maxPermits(2)
                .rate(2)
                .build();
        service.initLimitKey(info);
        boolean res;

        Thread.sleep(200);
        res = service.tryAcquire();
        log.info("tryAcquire-res: [{}]", res);

        Thread.sleep(1000);
        res = service.tryAcquire();
        log.info("tryAcquire-res: [{}]", res);
        res = service.tryAcquire();
        log.info("tryAcquire-res: [{}]", res);
        res = service.tryAcquire();
        log.info("tryAcquire-res: [{}]", res);
    }

}