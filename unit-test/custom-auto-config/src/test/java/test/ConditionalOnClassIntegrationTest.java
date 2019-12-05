package test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConditionalOnClassIntegrationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    public void whenDependentClassIsPresent_thenBeanCreated() {
        this.contextRunner.withUserConfiguration( ConditionalOnClassConfiguration.class )
                .run( context -> {
                    Object created = context.getBean( "created" );
                    Object missed = context.containsBean( "missed" );
                    Object bizService = context.containsBean( "bizService" );
                    System.out.println( "created value = " + created );
                    System.out.println( "has missed = " + missed );
                    System.out.println( "has bizService = " + bizService );
                } );
    }

    @Test
    public void whenDependentClassIsPresent_thenBeanMissing() {
        this.contextRunner.withUserConfiguration( ConditionalOnMissingClassConfiguration.class )
                .run( context -> {
                    Object created = context.containsBean( "created" );
                    Object missed = context.containsBean( "missed" );
                    System.out.println( "has created = " + created );
                    System.out.println( "has missed = " + missed );
                } );
    }

    @Test
    public void whenDependentClassIsNotPresent_thenBeanMissing() {
        this.contextRunner.withUserConfiguration( ConditionalOnClassConfiguration.class )
                .withClassLoader( new FilteredClassLoader( ConditionalOnClassIntegrationTest.class ) )
                .run( ( context ) -> {
                    Object created = context.containsBean( "created" );
                    Object missed = context.containsBean( "missed" );
                    System.out.println( "has created = " + created );
                    System.out.println( "has missed = " + missed );
                    assertThat( context ).doesNotHaveBean( ConditionalOnClassIntegrationTest.class );
                } );
    }

    @Test
    public void whenDependentClaXssIsNotPresent_thenBeanCreated() {
        this.contextRunner.withUserConfiguration( ConditionalOnMissingClassConfiguration.class )
                .withClassLoader( new FilteredClassLoader( ConditionalOnClassIntegrationTest.class ) )
                .run( ( context ) -> {
                    Object created = context.containsBean( "created" );
                    Object missed = context.containsBean( "missed" );
                    Object misseObj = context.getBean( "missed" );
                    System.out.println( "has created = " + created );
                    System.out.println( "has missed = " + missed );
                    System.out.println( "missed value = " + misseObj );
                    assertThat( context ).doesNotHaveBean( ConditionalOnClassIntegrationTest.class );
                } );
    }

    @Configuration
    @ConditionalOnClass( ConditionalOnClassIntegrationTest.class )
    protected static class ConditionalOnClassConfiguration {
        @Bean
        public String created() {
            return "This is created when ConditionalOnClassIntegrationTest is present on the classpath";
        }
    }

    @Configuration
    @ConditionalOnMissingClass( "test.ConditionalOnClassIntegrationTest" )
    protected static class ConditionalOnMissingClassConfiguration {
        @Bean
        public String missed() {
            return "This is missed when ConditionalOnClassIntegrationTest is present on the classpath";
        }
    }

}
