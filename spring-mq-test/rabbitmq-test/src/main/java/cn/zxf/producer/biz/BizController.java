package cn.zxf.producer.biz;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zxf.common.Email;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Autowired
    AmqpTemplate rabbitTemplate;

    // http://localhost:9011/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        Email message = new Email( "zxf@s.cn", "== test == " + System.currentTimeMillis() % 1000 );
        System.out.println( "send => " + message );
        rabbitTemplate.convertAndSend( "test-obj-001", message );
        return "hello";
    }

}
