package cn.zxf.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerMainApplication {

    public static void main( String[] args ) {
        System.setProperty( "spring.profiles.active", "producer" );
        SpringApplication.run( ProducerMainApplication.class, args );
    }

}
