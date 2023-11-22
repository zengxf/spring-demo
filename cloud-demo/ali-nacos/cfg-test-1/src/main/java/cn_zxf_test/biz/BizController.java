package cn_zxf_test.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Value("${test.name}")
    String testName;
    @Value("${app.sign:none}")  // 不能实时刷新值
    String appSign;
    @Autowired
    AppCfg appCfg;              // 能刷新值


    // http://localhost:9001/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        return "hello --> " + testName + " ----------- " + appSign + " ----------- " + appCfg.getSign();
    }

}
