package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.config.Ser1Config;

/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
@Slf4j
@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Autowired
    private Ser1Config config;
    @Autowired
    private BizService bizService;

    // http://localhost:6881/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        log.info("hello --------------");
        return "hello --- " + config.getConfig1();
    }

}
