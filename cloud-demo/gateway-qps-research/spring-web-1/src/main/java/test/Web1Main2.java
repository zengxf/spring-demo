package test;

import org.springframework.boot.SpringApplication;

public class Web1Main2 {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "port2");
        SpringApplication.run(Web1Main1.class, args);
    }

}
