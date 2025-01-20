package cn_zxf_test.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${app.sign}")
    String appSign;

    // http://localhost:9901/test/header/nginx-add
    @GetMapping("/test/header/nginx-add")
    public String testHeaderNginxAdd() {
        return "hello --> " + appSign;
    }

}
