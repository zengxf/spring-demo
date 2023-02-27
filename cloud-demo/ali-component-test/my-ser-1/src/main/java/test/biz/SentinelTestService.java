package test.biz;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.config.Ser1Config;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
@Component
@Slf4j
public class SentinelTestService {

    @Autowired
    private Ser1Config config;

    @SentinelResource(value = "ser1-sentinel-test", blockHandler = "blockHandle")
    public String sayHello() {
        return "Hello, " + config.getConfig1();
    }

    @SentinelResource(value = "ser1-sentinel-get-user", fallback = "fallbackHandle")
    public String getUser(int i) {
        if (i % 3 == 0) {
            throw new RuntimeException("异常！i = " + i);
        }
        return "Hello, " + i;
    }

    public String blockHandle(BlockException e) { // 一定要用 BlockException 参数
        log.info("回退！", e);
        return "失败！";
    }

    public String fallbackHandle(int i, Throwable e) { // 一定要用 Throwable 参数
        log.info("回退！i = [{}], err: [{}]", i, e.getMessage());
        return "降级！i = " + i;
    }

    @SneakyThrows
    @SentinelResource(value = "ser1-sentinel-delay-get-user", fallback = "fallbackHandle")
    public String delayGetUser(int i) {
        if (i % 3 == 0) {
            Thread.sleep(300L);
        }
        return "Hello, " + i;
    }

    @SentinelResource(value = "ser1-sentinel-error-get-user", fallback = "fallbackHandle")
    public String errorGetUser(int i) {
        if (i % 3 == 0) {
            throw new RuntimeException("异常！i = " + i);
        }
        return "Hello, " + i;
    }

}
