package cn.zxf.producer.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zxf.common.Email;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Autowired
    KafkaTemplate<Object, Object> template;

    // http://localhost:9011/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        long sign = System.currentTimeMillis() % 1000;
        Email message = new Email( "zxf@s.cn", "== test == " + sign );
        System.out.println( "send => " + message );
        template.send( "test-obj", message );
        return "hello-" + sign;
    }

}
