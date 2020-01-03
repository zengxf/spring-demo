package test_mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@EnableBinding( value = { Source.class } )
public class SinkApplicationTest {
    
    @Autowired
    private Source source;

    @Test
    public void sinkSenderTester() {
        MessageBuilder<String> msg = MessageBuilder.withPayload( "test abc" );
        source.output()
                .send( msg.build() );
    }

}