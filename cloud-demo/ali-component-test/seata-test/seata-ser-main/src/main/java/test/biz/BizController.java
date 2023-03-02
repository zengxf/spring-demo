package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/biz")
public class BizController {

    // http://localhost:9682/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        log.info("hello --------------");
        return "hello";
    }

}
