package cn.zxf.producer.biz;

import java.io.IOException;
import java.time.LocalTime;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.zxf.common.Email;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    // http://localhost:9011/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        Email message = new Email( "zxf@s.cn", "== test == " + System.currentTimeMillis() % 1000 );
        System.out.println( "send => " + message );
        rabbitTemplate.convertAndSend( "test-obj-002", message );
        // rabbitTemplate.convertAndSend( "test-fanout-01", "", message );
        return "hello";
    }

    // http://localhost:9011/api/biz/test-delay
    @Deprecated
    @GetMapping( "test-delay" )
    public String testDelay() {
        Email body = new Email( "zxf@s.cn", "== test == " + System.currentTimeMillis() % 1000 );
        System.out.println( "[" + LocalTime.now() + "] send => " + body );

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString( body );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        MessageProperties properties = new MessageProperties();
        properties.setDelay( 5000 );

        Message message = new Message( json.getBytes(), properties );
        rabbitTemplate.send( "test-delay-zxf", "", message );
        return "OK - " + json;
    }

    // http://localhost:9011/api/biz/test-delay-2
    @GetMapping( "test-delay-2" )
    public String testDelay2() {
        Email body = new Email( "zxf@s.cn", "== test == " + System.currentTimeMillis() % 1000 );
        MessageProperties properties = new MessageProperties();
        properties.setDelay( 5000 );

        MessagePostProcessor processor = msg -> {
            return rabbitTemplate.getMessageConverter()
                    .toMessage( body, properties );
        };

        rabbitTemplate.convertAndSend( "test-delay-zxf", "", body, processor );
        System.out.println( "[" + LocalTime.now() + "] send => " + body );

        return "OK - " + body;
    }

}
