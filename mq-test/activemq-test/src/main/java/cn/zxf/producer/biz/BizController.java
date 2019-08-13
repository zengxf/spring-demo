package cn.zxf.producer.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zxf.common.Email;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Autowired
    JmsTemplate jmsTemplate;

    // http://localhost:9011/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        Email message = new Email( "zxf@s.cn", "== test == " + System.currentTimeMillis() % 1000 );
        System.out.println( "send => " + message );
        jmsTemplate.convertAndSend( "test-obj", message );
        return "hello";
    }

}
