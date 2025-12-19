package cn_zxf_test.biz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    // http://localhost:9001/api/biz/hello2
    @GetMapping("hello2")
    public Map<String, Object> hello2() {
        return Map.of(
                "name", testName,
                "thread", Thread.currentThread().toString(),
                "time", TimeUtils.curTimeStr()
        );
    }

}
