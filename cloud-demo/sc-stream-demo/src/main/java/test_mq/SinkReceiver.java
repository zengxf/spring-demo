package test_mq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import lombok.extern.slf4j.Slf4j;

/**
 * 可通过 MQ 的 Web 控制台发消息测试
 * <p>
 * Created by zengxf on 2020-01-03
 */
@Slf4j
@EnableBinding( Sink.class )
public class SinkReceiver {

    @StreamListener( Sink.INPUT )
    public void receive( Object payload ) {
        log.info( "Received: " + payload );
    }

}
