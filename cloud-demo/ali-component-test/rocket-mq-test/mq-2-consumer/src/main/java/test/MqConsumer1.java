package test;

import org.springframework.boot.SpringApplication;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
public class MqConsumer1 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MqConsumerAnnotation.class);
        app.setAdditionalProfiles("dev1");
        app.run(args);
    }

}
