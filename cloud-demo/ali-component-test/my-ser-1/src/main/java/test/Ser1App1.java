package test;

import org.springframework.boot.SpringApplication;

public class Ser1App1 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Ser1AppAnnotation.class);
        app.setAdditionalProfiles("dev1");
        app.run(args);
    }

}
