package test;

import org.springframework.boot.SpringApplication;

/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
public class Ser1App2 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Ser1AppAnnotation.class);
        app.setAdditionalProfiles("dev2");
        app.run(args);
    }

}
