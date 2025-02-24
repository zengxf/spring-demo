package cn_zxf_test.biz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Value("${app.sign}")
    String appSign;

    // http://localhost:9901/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        return "hello --> " + appSign;
    }

}
