package cn.zxf.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Consumer2MainApplication {

    public static void main( String[] args ) {
        System.setProperty( "spring.profiles.active", "consumer2" );
        SpringApplication.run( Consumer2MainApplication.class, args );
    }

}
