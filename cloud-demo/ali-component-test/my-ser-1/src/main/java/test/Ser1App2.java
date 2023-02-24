package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Ser1App2 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Ser1App2.class);
        app.setAdditionalProfiles("dev2");
        app.run(args);
    }

}
