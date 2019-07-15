package cn.zxf.producer;

import java.util.List;

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
        return new Queue( "test-obj-001" );
    }

    // 此配置没用
    @Bean
    public List<Queue> listQueue() {
        return List.of( //
                new Queue( "test-list-1" ), //
                new Queue( "test-list-2" ), //
                new Queue( "test-list-3" ) //
        );
    }

    @Bean
    public RabbitTemplate rabbitTemplate( final ConnectionFactory factory ) {
        final RabbitTemplate template = new RabbitTemplate( factory );
        template.setMessageConverter( new Jackson2JsonMessageConverter() );
        return template;
    }

}
