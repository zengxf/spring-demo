package test;

import org.springframework.boot.SpringApplication;

public class DSerApp2 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DSerAppAnnotation.class);
        app.setAdditionalProfiles("dev2");
        app.run(args);
    }

}
