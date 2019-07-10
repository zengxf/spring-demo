package cn.zxf.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import cn.zxf.common.Email;

@Component
public class Receiver {

    @JmsListener( destination = "test-obj" )
    public void receiveMessage( Email email ) {
        System.out.println( "Received <" + email + ">" );
    }

}