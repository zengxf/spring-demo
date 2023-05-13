package test.biz;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.user.UserDto;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/biz")
@Slf4j
public class BizController {

    @Autowired
    private BizService service;

    // http://localhost:9066/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    // err: http://localhost:9066/api/biz/getUsers?size=-1
    // err: http://localhost:9066/api/biz/getUsers?size=6
    // ok:  http://localhost:9066/api/biz/getUsers?size=1
    @GetMapping("getUsers")
    public List<UserDto> getUsers(
            @RequestParam(defaultValue = "3") int size
    ) {
        log.info("req-param -> size: [{}]", size);
        return service.getUsers(size);
    }

    // ok:  http://localhost:9066/api/biz/getUsers2?size=1&idPrefix=id-
    @GetMapping("getUsers2")
    public List<UserDto> getUsers2(
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id-") String idPrefix
    ) {
        log.info("req-param -> size: [{}], idPrefix: [{}]", size, idPrefix);
        return service.getUsers(size, idPrefix);
    }

    // http://localhost:9066/api/biz/sleepMs?ms=20
    // http://localhost:9066/api/biz/sleepMs?ms=45
    @GetMapping("sleepMs")
    public Map<String, Object> sleepMs(
            @RequestParam(defaultValue = "10") int ms
    ) throws InterruptedException {
        log.info("req-param -> ms: [{}]", ms);
        service.sleepMs(ms);
        int ms1 = service.sleep5Ms();
        log.info("ms1: [{}]", ms1);
        log.info("--------- test [xxx] --------");
        return Map.of("ms", ms + ms1, "sign", "OK!");
    }

    /**
     * 头的 key 不能包含空格，但头的 value 可以包含空格；
     * Tomcat 9 会将 value 的空格清除，但 Tomcat 早期版本可能不会清除
     */
    // http://localhost:9066/api/biz/herder
    @GetMapping("herder")
    public Object herder(HttpServletRequest request) {
        log.info("-------------");
        Map<String, Object> map = new LinkedHashMap<>();
        { // Header
            Enumeration<String> keys = request.getHeaderNames();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                String value = request.getHeader(key);
                log.info("[{}] = [{}]", key, value);
                map.put(key, value);
            }
        }
        { // Param
            Enumeration<String> keys = request.getParameterNames();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                String[] values = request.getParameterValues(key);
                log.info("[{}] = [{}]", key, values);
                map.put(key, values);
            }
        }
        log.info("-------------");
        return map;
    }

}
