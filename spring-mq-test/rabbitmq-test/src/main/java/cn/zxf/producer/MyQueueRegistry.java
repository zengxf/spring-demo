package cn.zxf.producer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyQueueRegistry implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory ) throws BeansException {
    }

    @Override
    public void postProcessBeanDefinitionRegistry( BeanDefinitionRegistry registry ) throws BeansException {
        // simple( registry );

        String exName = "test-delay-zxf";
        BeanDefinition exDef = BeanDefinitionBuilder.genericBeanDefinition( CustomExchange.class )
                .addConstructorArgValue( exName )
                .addConstructorArgValue( "x-delayed-message" ) // type
                .addConstructorArgValue( true ) // durable
                .addConstructorArgValue( false ) // autoDelete
                .addConstructorArgValue( Map.of( "x-delayed-type", "direct" ) )
                .getRawBeanDefinition();
        registry.registerBeanDefinition( exName, exDef ); // 注册 Bean
        System.out.println( "注册 CustomExchange：" + exName );

        String queueName = "test-delay-queue";
        BeanDefinition queueDef = BeanDefinitionBuilder.genericBeanDefinition( Queue.class )
                .addConstructorArgValue( queueName )
                .addConstructorArgValue( true )
                .addConstructorArgValue( false )
                .addConstructorArgValue( false )
                .addConstructorArgValue( null ) // new HashMap<String, Object>()
                .getRawBeanDefinition();
        registry.registerBeanDefinition( queueName, queueDef ); // 注册 Bean
        System.out.println( "注册 Queue：" + queueName );

        BeanDefinition bindDef = BeanDefinitionBuilder.genericBeanDefinition( Binding.class )
                .addConstructorArgValue( queueName )
                .addConstructorArgValue( DestinationType.QUEUE )
                .addConstructorArgValue( exName )
                .addConstructorArgValue( "" )
                .addConstructorArgValue( new HashMap<String, Object>() )
                .getRawBeanDefinition();
        registry.registerBeanDefinition( exName + "-" + queueName, bindDef ); // 注册 Bean
        System.out.println( "注册 Binding：" + exName + "-" + queueName );
    }

    void simple( BeanDefinitionRegistry registry ) {
        List<String> queues = List.of( "test-list-1", "test-list-2", "test-list-3" );
        Map<String, List<String>> map = Map.of( "test-fanout-01", queues );

        map.forEach( ( exName, list ) -> {
            BeanDefinition exDef = BeanDefinitionBuilder.genericBeanDefinition( FanoutExchange.class )
                    .addConstructorArgValue( exName )
                    .addConstructorArgValue( true )
                    .addConstructorArgValue( false )
                    .addConstructorArgValue( null ) // new HashMap<String, Object>()
                    .getRawBeanDefinition();
            registry.registerBeanDefinition( exName, exDef ); // 注册 Bean
            System.out.println( "注册 FanoutExchange：" + exName );

            list.forEach( queueName -> {
                BeanDefinition queueDef = BeanDefinitionBuilder.genericBeanDefinition( Queue.class )
                        .addConstructorArgValue( queueName )
                        .addConstructorArgValue( true )
                        .addConstructorArgValue( false )
                        .addConstructorArgValue( false )
                        .addConstructorArgValue( null ) // new HashMap<String, Object>()
                        .getRawBeanDefinition();
                registry.registerBeanDefinition( queueName, queueDef ); // 注册 Bean
                System.out.println( "注册 Queue：" + queueName );

                BeanDefinition bindDef = BeanDefinitionBuilder.genericBeanDefinition( Binding.class )
                        .addConstructorArgValue( queueName )
                        .addConstructorArgValue( DestinationType.QUEUE )
                        .addConstructorArgValue( exName )
                        .addConstructorArgValue( "" )
                        .addConstructorArgValue( new HashMap<String, Object>() )
                        .getRawBeanDefinition();
                registry.registerBeanDefinition( exName + "-" + queueName, bindDef ); // 注册 Bean
                System.out.println( "注册 Binding：" + exName + "-" + queueName );
            } );
        } );
    }

}
