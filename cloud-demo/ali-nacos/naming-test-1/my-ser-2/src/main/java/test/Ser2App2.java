package test;

import org.springframework.boot.SpringApplication;


/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
@Deprecated // 不需要
public class Ser2App2 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Ser2AppAnnotation.class);
        app.setAdditionalProfiles("dev2");
        // app.run(args);
    }

}
