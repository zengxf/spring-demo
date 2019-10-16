package test.set_singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplication {

    public static void main( String[] args ) {
        @SuppressWarnings( "resource" )
        ApplicationContext context = new ClassPathXmlApplicationContext( "/set-singleton-bean.xml" );
        StudentA bean = context.getBean( StudentA.class );
        System.out.println( bean );
    }

}
