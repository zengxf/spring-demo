package test;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApplication {

    public static void main( String[] args ) {
        String[] arr = { "=test=", "--name=zxf", "--age=22" };
        ConfigurableApplicationContext context = SpringApplication.run( MainApplication.class, arr );
        ApplicationArguments applicationArguments = context.getBean( ApplicationArguments.class );
        System.out.println( "============" );
        System.out.println( "name====" + applicationArguments.getOptionNames() );
        System.out.println( "values====" + applicationArguments.getOptionValues( "name" ) );
    }

}
