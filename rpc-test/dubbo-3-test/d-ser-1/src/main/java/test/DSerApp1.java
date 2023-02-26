package test;

import org.springframework.boot.SpringApplication;

public class DSerApp1 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DSerAppAnnotation.class);
        app.setAdditionalProfiles("dev1");
        app.run(args);
    }

}
