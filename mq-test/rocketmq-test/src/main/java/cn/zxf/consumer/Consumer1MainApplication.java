package cn.zxf.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Consumer1MainApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "consumer1");
        SpringApplication.run(Consumer1MainApplication.class, args);
    }

}
