package test_mq2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;

@SpringBootApplication
@EnableBinding( { Source.class, Sink.class } ) // 1
public class SendAndReceiveApplication {

    public static void main( String[] args ) {
        SpringApplication.run( SendAndReceiveApplication.class, args );
    }

    @Bean // 2
    public CustomRunner customRunner() {
        return new CustomRunner();
    }

    public static class CustomRunner implements CommandLineRunner {
        @Autowired
        private Source source;

        @Override
        public void run( String... args ) throws Exception {
            int count = 5;
            for ( int index = 1; index <= count; index++ ) {
                MessageBuilder<String> msg = MessageBuilder.withPayload( "msg-" + index );
                source.output()
                        .send( msg.build() ); // 3
            }
        }
    }
}