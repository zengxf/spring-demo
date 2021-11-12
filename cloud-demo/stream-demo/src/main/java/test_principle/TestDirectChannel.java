package test_principle;

import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;

public class TestDirectChannel {

    public static void main( String[] args ) {
        DirectChannel messageChannel = new DirectChannel(); // 1
        messageChannel.subscribe( msg -> {
            System.out.println( "receive1: " + msg.getPayload() );
        } );
        messageChannel.subscribe( msg -> {
            System.out.println( "receive2: " + msg.getPayload() );
        } );
        MessageBuilder<String> msg = MessageBuilder.withPayload( "msg from test 1" );
        MessageBuilder<String> msg2 = MessageBuilder.withPayload( "msg from test 2" );
        messageChannel.send( msg.build() ); // 3
        messageChannel.send( msg2.build() ); // 3
    }

}
