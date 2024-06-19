package cn.zxf.producer.biz;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zxf.common.Email;

@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Autowired
    RocketMQTemplate rocketTemplate;

    // http://localhost:9011/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        Email message = new Email("zxf@sina.cn", "== test == " + System.currentTimeMillis() % 1000);

        System.out.println("send => " + message);

        rocketTemplate.convertAndSend("test-obj", message);

        return "hello-ok";
    }

}
