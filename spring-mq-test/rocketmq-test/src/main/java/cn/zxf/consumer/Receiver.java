package cn.zxf.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import cn.zxf.common.Email;

@Component
@RocketMQMessageListener( topic = "test-obj", consumerGroup = "my-consumer-1" )
public class Receiver implements RocketMQListener<Email> {

    public void onMessage( Email email ) {
        System.out.println( "Received <" + email + ">" );
    }

}