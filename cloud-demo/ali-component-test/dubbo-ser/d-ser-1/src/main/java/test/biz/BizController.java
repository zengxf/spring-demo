package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.config.DSer1Config;

@Slf4j
@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Autowired
    private DSer1Config config;
    @Autowired
    private SentinelTestService sentinelTestService;

    // http://localhost:6981/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        log.info("hello --------------");
        return "hello --- " + config.getConfig1();
    }

    // http://localhost:6981/api/biz/sentinel-hello
    @GetMapping("sentinel-hello")
    public String sentinelHello() {
        log.info("sentinel hello --------------");
        return sentinelTestService.sayHello();
    }

    // http://localhost:6981/api/biz/sentinel-test
    @GetMapping("sentinel-test")
    public String sentinelTest() {
        log.info("sentinel test --------------");
        return "hello --- " + config.getConfig1();
    }

}
