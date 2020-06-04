package cn.zxf.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cn.zxf.starter.error.MyExceptionHandler;

@Configuration
@ConditionalOnClass( ExampleService.class )
@EnableConfigurationProperties( ExampleServiceProperties.class )
@Import( MyExceptionHandler.class )
public class ExampleAutoConfigure {

    @Autowired
    private ExampleServiceProperties cfg;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty( prefix = "example.service", value = "enabled", havingValue = "true" )
    ExampleService exampleService() {
        System.out.println( "===== 启动 service =====" );
        return new ExampleService( cfg.getPrefix(), cfg.getSuffix() );
    }

}