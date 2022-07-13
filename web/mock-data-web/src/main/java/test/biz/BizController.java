package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/biz")
@Slf4j
public class BizController {

    // http://localhost:9066/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    /**
     * 头的 key 不能包含空格，但头的 value 可以包含空格；
     * Tomcat 9 会将 value 的空格清除，但 Tomcat 早期版本不会清除
     */
    // http://localhost:9066/api/biz/herder
    @GetMapping("herder")
    public Object herder(HttpServletRequest request) {
        log.info("-------------");
        Enumeration<String> keys = request.getHeaderNames();
        Map<String, String> map = new LinkedHashMap<>();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = request.getHeader(key);
            log.info("[{}] = [{}]", key, value);
            map.put(key, value);
        }
        log.info("-------------");
        return map;
    }

}
