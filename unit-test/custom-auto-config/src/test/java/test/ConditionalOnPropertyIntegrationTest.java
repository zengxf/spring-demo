package test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import test.biz.CustomService;
import test.biz.DefaultService;
import test.biz.SimpleService;

public class ConditionalOnPropertyIntegrationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    public void whenGivenCustomProperttyValue_thenCustomServiceCreated() {
        this.contextRunner.withPropertyValues( "com.baeldung.service=custom" )
                .withUserConfiguration( SimpleServiceConfiguration.class )
                .run( context -> {
                    Object defaultService = context.containsBean( "defaultService" );
                    System.out.println( "has defaultService = " + defaultService );
                    Object customService = context.containsBean( "customService" );
                    System.out.println( "has customService = " + customService );
                    SimpleService simpleService = context.getBean( CustomService.class );
                    System.out.println( "serve = " + simpleService.serve() );
                } );
    }

    @Test
    public void whenGivenDefaultPropertyValue_thenDefaultServiceCreated() {
        this.contextRunner.withPropertyValues( "com.baeldung.service=default" )
                .withUserConfiguration( SimpleServiceConfiguration.class )
                .run( context -> {
                    Object defaultService = context.containsBean( "defaultService" );
                    System.out.println( "has defaultService = " + defaultService );
                    Object customService = context.containsBean( "customService" );
                    System.out.println( "has customService = " + customService );
                    SimpleService simpleService = context.getBean( DefaultService.class );
                    System.out.println( "serve = " + simpleService.serve() );
                } );
    }

    @Configuration
    protected static class SimpleServiceConfiguration {
        @Bean
        @ConditionalOnProperty( name = "com.baeldung.service", havingValue = "default" )
        @ConditionalOnMissingBean
        public DefaultService defaultService() {
            return new DefaultService();
        }

        @Bean
        @ConditionalOnProperty( name = "com.baeldung.service", havingValue = "custom" )
        @ConditionalOnMissingBean
        public CustomService customService() {
            return new CustomService();
        }
    }

}