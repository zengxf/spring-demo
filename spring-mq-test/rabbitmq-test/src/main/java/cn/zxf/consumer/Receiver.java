package cn.zxf.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.zxf.common.Email;

@Component
public class Receiver {

    @Async
    @RabbitListener( queues = "test-obj" )
    public void receiveMessage( Email email ) {
        System.out.println( Thread.currentThread()
                .getName() + " => Received <" + email + ">" );
    }

}