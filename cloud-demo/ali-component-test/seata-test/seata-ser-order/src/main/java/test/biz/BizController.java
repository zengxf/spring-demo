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
    private OrderService orderService;

    // http://localhost:6851/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        log.info("hello --------------");
        return "hello";
    }

    // http://localhost:6851/api/biz/operate?sign=1
    @GetMapping("operate")
    public String operate(
            @RequestParam Integer sign,
            @RequestParam(defaultValue = "1") Integer uid,
            @RequestParam(defaultValue = "10") Integer money
    ) {
        orderService.operate(sign, uid, money);
        return "OK";
    }

}
