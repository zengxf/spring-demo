package cn.zxf.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import cn.zxf.common.Email;

@Component
public class Receiver {

    @RabbitListener( queues = "test-obj-001" )
    public void receiveMessage1( Email email ) {
        System.out.println( "test-1 => Received <" + email + ">" );
    }

    @RabbitListener( queues = "test-obj-001" )
    public void receiveMessage2( Email email ) {
        System.out.println( "test-2 => Received <" + email + ">" );
    }

}