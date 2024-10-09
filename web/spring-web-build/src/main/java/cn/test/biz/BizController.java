package cn.test.biz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Value("${test.name}")
    String testName;

    // http://localhost:9001/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        return "hello --> " + testName;
    }

}
