package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Value("${spring.application.name:000}")
    private String appName;
    @Value("${my.sign:000}")
    private String sign;


    // http://localhost:9681/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        log.info("hello -------------- {}",sign);
        return "Hello from " + sign;
    }

    // -------------------------------------

    // mock 请求 1
    // http://localhost:9681/api/biz/mockReq1
    @GetMapping("mockReq1")
    public Map<String, Object> mockReq1() {
        log.info("mockReq 1 -------------- {}",sign);

        Map<String, Object> res = buildRes();
        res.put("data", "OK 1");

        return res;
    }

    // mock 请求 2
    // http://localhost:9681/api/biz/mockReq2
    @GetMapping("mockReq2")
    public Map<String, Object> mockReq2() {
        log.info("mockReq2 -------------- {}",sign);

        Map<String, Object> res = buildRes();
        res.put("data", "OK 2");

        return res;
    }

    // -------------------------------------

    // mock 大流量 1k
    // http://localhost:9681/api/biz/mockFlow1k
    @GetMapping("mockFlow1k")
    public Map<String, Object> mockFlow1k() {
        log.info("mockFlow 1k -------------- {}",sign);

        Map<String, Object> res = buildRes();
        res.put("data", mackData(1));

        return res;
    }

    // mock 大流量 2k
    // http://localhost:9681/api/biz/mockFlow2k
    @GetMapping("mockFlow2k")
    public Map<String, Object> mockFlow2k() {
        log.info("mockFlow 2k -------------- {}",sign);

        Map<String, Object> res = buildRes();
        res.put("data", mackData(2));

        return res;
    }

    // -------------------------------------

    private Map<String, Object> buildRes() {
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("code", 200);
        res.put("appName", appName);
        res.put("sign", sign);
        res.put("dateTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        return res;
    }

    static List<String> mackData(int nk) {
        List<String> data = new ArrayList<>();

        String line = IntStream.rangeClosed(1, 50)
                .mapToObj(Integer::toHexString)
                .collect(Collectors.joining("-"))
                .substring(0, 96);

        IntStream.rangeClosed(1, nk * 10)
                .mapToObj(i -> String.format("%2d # ", i) + line)
                .forEach(data::add);

        int len = data.toString().length();
        log.info("data.size(): [{}]", len);

        return data;
    }

}
