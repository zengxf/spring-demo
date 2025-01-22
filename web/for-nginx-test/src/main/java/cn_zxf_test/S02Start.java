package cn_zxf_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <p/>
 * Created by ZXFeng on 2025/1/22
 */
public class S02Start {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder()
                .sources(Application.class)
                .profiles(Application.S02)
                .build(args);
        app.run();
    }

}
