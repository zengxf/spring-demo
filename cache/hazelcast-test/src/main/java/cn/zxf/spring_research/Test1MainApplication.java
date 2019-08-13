package cn.zxf.spring_research;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Test1MainApplication {

    public static void main( String[] args ) {
        System.setProperty( "spring.profiles.active", "test1" );
        SpringApplication.run( Test1MainApplication.class, args );
    }

}
