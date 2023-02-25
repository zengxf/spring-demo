package test.biz;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.config.Ser1Config;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
@Component
public class SentinelTestService {

    @Autowired
    private Ser1Config config;

    @SentinelResource(value = "ser1-sentinel-test")
    public String sayHello() {
        return "Hello, " + config.getConfig1();
    }

}
