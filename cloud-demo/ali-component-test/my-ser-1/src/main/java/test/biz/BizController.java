package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.config.Ser1Config;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Autowired
    private Ser1Config config;
    @Autowired
    private SentinelTestService sentinelTestService;

    // http://localhost:6881/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        log.info("hello --------------");
        return "hello --- " + config.getConfig1();
    }

    // http://localhost:6881/api/biz/sentinel-hello
    @GetMapping("sentinel-hello")
    public String sentinelHello() {
        log.info("sentinel hello --------------");
        return sentinelTestService.sayHello();
    }

    // http://localhost:6881/api/biz/sentinel-test
    @GetMapping("sentinel-test")
    public String sentinelTest() {
        log.info("sentinel test --------------");
        return "hello --- " + config.getConfig1();
    }

    // http://localhost:6881/api/biz/get-array
    @GetMapping("get-array")
    public List<String> getArray() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(i -> "test-str-" + config.getConfig1() + "----" + i)
                .collect(Collectors.toList());
    }


    // http://localhost:6881/api/biz/echo
    @GetMapping("echo")
    public Map<String, Object> echo(HttpServletRequest req) {
        log.info("hello --------------");
        Map<String, Object> res = new LinkedHashMap<>();
        Enumeration<String> keys = req.getHeaderNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String v = req.getHeader(key);
            res.put(key, v);
        }
        res.put("app-sign", config.getConfig1());
        return res;
    }

}
