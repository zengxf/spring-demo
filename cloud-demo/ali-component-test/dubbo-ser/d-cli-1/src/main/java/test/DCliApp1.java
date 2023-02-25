package test;

import org.springframework.boot.SpringApplication;

public class DCliApp1 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DCli1AppAnnotation.class);
        app.setAdditionalProfiles("dev1");
        app.run(args);
    }

}
