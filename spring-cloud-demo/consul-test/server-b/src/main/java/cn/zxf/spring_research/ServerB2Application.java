package cn.zxf.spring_research;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerB2Application {

    public static void main( String[] args ) {
        System.setProperty( "spring.profiles.active", "p02" );
        SpringApplication.run( ServerB2Application.class, args );
    }

}
