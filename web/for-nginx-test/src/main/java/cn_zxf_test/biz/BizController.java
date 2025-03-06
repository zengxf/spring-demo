package cn_zxf_test.biz;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/biz")
@Slf4j
public class BizController {

    @Value("${app.sign}")
    String appSign;


    // http://localhost:9901/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        return "hello --> " + appSign;
    }

    // http://localhost:9901/api/biz/get-json
    @GetMapping("/get-json")
    public Map<String, Object> getJson() {
        return Map.of(
                "k-int", 2121,
                "k-str", "ok",
                "k-date", new Date()
        );
    }

    // http://localhost:9901/api/biz/post-test
    @PostMapping("/post-test")
    public Map<String, Object> postTest(
            @RequestBody Map<String, Object> map
    ) {

        ThreadUtil.safeSleep(100L);
        log.info("post-test-params: {}", map);

        return Map.of(
                "k-int", 6688,
                "k-str", "ok-xx",
                "k-date", new Date()
        );
    }

}
