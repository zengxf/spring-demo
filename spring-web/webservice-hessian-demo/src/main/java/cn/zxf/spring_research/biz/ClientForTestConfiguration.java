package cn.zxf.spring_research.biz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class ClientForTestConfiguration {
    
    @Bean
    public HessianProxyFactoryBean helloClient() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl( "http://localhost:9011/HelloService" );
        factory.setServiceInterface( HelloService.class );
        return factory;
    }
    
}
