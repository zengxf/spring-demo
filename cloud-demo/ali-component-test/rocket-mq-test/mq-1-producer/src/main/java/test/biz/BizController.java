package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Autowired
    private BizService service;

    // http://localhost:6821/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        log.info("hello --------------");
        return "hello";
    }

    // http://localhost:6821/api/biz/operate?topic=test-1&message=test-msg-123
    @GetMapping("operate")
    public String operate(
            @RequestParam(defaultValue = "test-1") String topic,
            @RequestParam(defaultValue = "test-msg") String message
    ) {
        service.sendMsg(topic, message);
        return "OK";
    }

}
