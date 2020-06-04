package cn.zxf.consumer;

import java.time.LocalTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import cn.zxf.common.Email;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Receiver {

    @RabbitListener( queues = "test-obj" )
    public void receiveMessage1( Email email ) {
        log.info( "test-1 => Received <" + email + ">" );
    }

    @RabbitListener( queues = "test-obj" )
    public void receiveMessage2( Email email ) {
        log.info( "test-2 => Received <" + email + ">" );
    }

    @RabbitListener( queues = "test-obj" )
    // @RabbitListener( queues = "test-delay-queue" )
    public void receiveDelayMessage( Email email ) {
        log.info( "[" + LocalTime.now() + "] test-delay => Received <" + email + ">" );
    }

}