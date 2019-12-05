package test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConditionalOnBeanIntegrationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    public void whenDependentBeanIsPresent_thenConditionalBeanCreated() {
        this.contextRunner.withUserConfiguration( BasicConfiguration.class, ConditionalOnBeanConfiguration.class )
                .run( ( context ) -> {
                    Object created = context.getBean( "created" );
                    Object createOnBean = context.getBean( "createOnBean" );
                    System.out.println( "created value = " + created );
                    System.out.println( "createOnBean value = " + createOnBean );
                } );
    }

    @Test
    public void whenDependentBeanIsPresent_thenConditionalMissingBeanIgnored() {
        this.contextRunner.withUserConfiguration( BasicConfiguration.class, ConditionalOnMissingBeanConfiguration.class )
                .run( ( context ) -> {
                    Object created = context.getBean( "created" );
                    Object createOnMissingBean = context.containsBean( "createOnMissingBean" );
                    System.out.println( "created value = " + created );
                    System.out.println( "has createOnMissingBean = " + createOnMissingBean );
                } );
    }

    @Test
    public void whenDependentBeanIsNotPresent_thenConditionalMissingBeanCreated() {
        this.contextRunner.withUserConfiguration( ConditionalOnMissingBeanConfiguration.class )
                .run( ( context ) -> {
                    Object hasCreateOnMissingBean = context.containsBean( "createOnMissingBean" );
                    System.out.println( "has createOnMissingBean = " + hasCreateOnMissingBean );
                    Object createOnMissingBean = context.getBean( "createOnMissingBean" );
                    System.out.println( "createOnMissingBean value = " + createOnMissingBean );
                } );
    }

    @Configuration
    protected static class BasicConfiguration {
        @Bean
        public String created() {
            return "This is always created";
        }
    }

    @Configuration
    @ConditionalOnBean( name = "created" )
    protected static class ConditionalOnBeanConfiguration {
        @Bean
        public String createOnBean() {
            return "This is created when bean (name=created) is present";
        }
    }

    @Configuration
    @ConditionalOnMissingBean( name = "created" )
    protected static class ConditionalOnMissingBeanConfiguration {
        @Bean
        public String createOnMissingBean() {
            return "This is created when bean (name=created) is missing";
        }
    }

}