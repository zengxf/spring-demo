package cn.zxf.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {

    @Bean
    public Queue testObjQueue() {
        return new Queue( "test-obj" );
    }

    @Bean
    public RabbitTemplate rabbitTemplate( final ConnectionFactory factory ) {
        final RabbitTemplate template = new RabbitTemplate( factory );
        template.setMessageConverter( new Jackson2JsonMessageConverter() );
        return template;
    }

}
