package cn.zxf.consumer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class JmsConfig {

    @Bean
    public Queue testObjQueue() {
        return new Queue( "test-obj" );
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory( ConnectionFactory connectionFactory ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory( connectionFactory );
        factory.setMessageConverter( new Jackson2JsonMessageConverter() );
        return factory;
    }

}
