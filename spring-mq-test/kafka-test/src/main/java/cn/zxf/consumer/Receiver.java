package cn.zxf.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import cn.zxf.common.Email;

@Component
public class Receiver {

    @KafkaListener( topics = "test-obj" )
    public void onMessage( Email email ) {
        System.out.println( "Received <" + email + ">" );
    }

}