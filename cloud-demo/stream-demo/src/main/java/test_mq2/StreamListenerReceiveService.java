package test_mq2;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class StreamListenerReceiveService {

    @StreamListener( Sink.INPUT ) // 4
    public void receiveByStreamListener1( String receiveMsg ) {
        System.out.println( "receive: " + receiveMsg );
    }

}
