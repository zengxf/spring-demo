package cn.zxf.spring_research;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Test2MainApplication {

    public static void main( String[] args ) {
        System.setProperty( "spring.profiles.active", "test2" );
        SpringApplication.run( Test2MainApplication.class, args );
    }

}
