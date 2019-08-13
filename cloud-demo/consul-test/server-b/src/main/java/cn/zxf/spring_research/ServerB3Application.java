package cn.zxf.spring_research;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerB3Application {

    public static void main( String[] args ) {
        System.setProperty( "spring.profiles.active", "p03" );
        SpringApplication.run( ServerB3Application.class, args );
    }

}
