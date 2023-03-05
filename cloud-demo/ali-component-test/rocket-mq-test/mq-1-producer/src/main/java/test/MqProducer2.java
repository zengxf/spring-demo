package test;

import org.springframework.boot.SpringApplication;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
public class MqProducer2 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MqProducerAnnotation.class);
        app.setAdditionalProfiles("dev2");
        app.run(args);
    }

}
