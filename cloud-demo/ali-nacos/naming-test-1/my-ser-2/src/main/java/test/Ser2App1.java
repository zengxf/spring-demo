package test;

import org.springframework.boot.SpringApplication;


/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
public class Ser2App1 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Ser2AppAnnotation.class);
        app.setAdditionalProfiles("dev1");
        app.run(args);
    }

}
