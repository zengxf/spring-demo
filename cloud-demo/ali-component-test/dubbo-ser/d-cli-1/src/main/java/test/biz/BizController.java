package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.config.Ser1Config;
import test.dubbo.api.DUserDTO;
import test.dubboconsumer.UserApiConsumer;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Autowired
    private Ser1Config config;
    @Autowired
    private UserApiConsumer consumer;

    // http://localhost:6991/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        log.info("hello --------------");
        return "hello --- " + config.getConfig1();
    }

    // http://localhost:6991/api/biz/getUserOne
    @GetMapping("getUserOne")
    public DUserDTO getUserOne() {
        log.info("hello --------------");
        return consumer.getUser("rest");
    }

    // http://localhost:6991/api/biz/getUser5
    @GetMapping("getUser5")
    public List<DUserDTO> getUser5() {
        log.info("hello --------------");
        return consumer.getUsers(5);
    }

}
