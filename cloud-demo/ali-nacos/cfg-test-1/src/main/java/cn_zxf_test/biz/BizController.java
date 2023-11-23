package cn_zxf_test.biz;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/biz")
@Slf4j
public class BizController {

    @Value("${test.name}")
    String testName;
    @Value("${app.sign:none}")  // 不能实时刷新值
    String appSign;
    @Autowired
    AppCfg appCfg;              // 能刷新值

    @PostConstruct
    public void init() {
        log.info("== BizController init! ==");
        log.info("{} --- {} --- {}", testName, appCfg, appCfg.sign);
    }

    // http://localhost:9001/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        log.info("{} --- {} --- {}", testName, appCfg, appCfg.sign);
        return "hello --> " + testName + " ----------- " + appSign + " ----------- " + appCfg.sign;
    }

}
