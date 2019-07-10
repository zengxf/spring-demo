package cn.zxf.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import cn.zxf.common.Email;

@Component
@RabbitListener(queues = "test-obj")
public class Receiver {

    @RabbitHandler
    public void receiveMessage( Email email ) {
        System.out.println( "Received <" + email + ">" );
    }

}