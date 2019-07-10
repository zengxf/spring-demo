package cn.zxf.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import cn.zxf.common.Email;

@Component
public class Receiver {

    @RabbitListener( queues = "test-obj" )
    public void receiveMessage( Email email ) {
        System.out.println( "Received <" + email + ">" );
    }

}