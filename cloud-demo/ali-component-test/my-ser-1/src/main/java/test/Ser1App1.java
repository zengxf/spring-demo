package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Ser1App1 {

    public static void main(String[] args) {
        // SpringApplication.run(MainApplication1.class, args);
        SpringApplication app = new SpringApplication(Ser1App1.class);
        app.setAdditionalProfiles("dev1");
        app.run(args);
    }

}
