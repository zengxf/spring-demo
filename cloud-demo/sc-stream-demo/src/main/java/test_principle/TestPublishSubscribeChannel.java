package test_principle;

import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.SubscribableChannel;

public class TestPublishSubscribeChannel {

    public static void main( String[] args ) {
        SubscribableChannel messageChannel = new PublishSubscribeChannel();
        messageChannel.subscribe(msg -> {
          System.out.println("receive1: " + msg.getPayload());
        });
        messageChannel.subscribe(msg -> {
          System.out.println("receive2: " + msg.getPayload());
        });
        MessageBuilder<String> msg = MessageBuilder.withPayload( "msg from test 1" );
        MessageBuilder<String> msg2 = MessageBuilder.withPayload( "msg from test 2" );
        messageChannel.send( msg.build() ); // 3
        messageChannel.send( msg2.build() ); // 3
    }
    
}
