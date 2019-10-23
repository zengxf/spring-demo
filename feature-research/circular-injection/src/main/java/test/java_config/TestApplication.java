package test.java_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// @Import( { ConfigurationA.class, BeanB.class, } ) // OK
// @Import( { ConfigurationA.class, BeanB.class, ServiceA.class, } ) // OK
// @Import( { ServiceA.class, BeanB.class, BeanA.class, } ) // OK
@Import( { ServiceA.class, ConfigurationA.class, BeanB.class, } ) // ERROR
public class TestApplication {

    public static void main( String[] args ) {
        SpringApplication.run( TestApplication.class, args );
    }

}
