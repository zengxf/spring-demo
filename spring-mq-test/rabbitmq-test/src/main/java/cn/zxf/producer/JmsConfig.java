package cn.zxf.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {

    @Bean
    public Queue testObjQueue() {
        return new Queue( "test-obj" );
    }

}
