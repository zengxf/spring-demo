package cn_zxf_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * s04 标识启动
 * <p/>
 * Created by ZXFeng on 2025/1/23
 */
public class S04Start {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder()
                .sources(Application.class)
                .profiles(Application.S04)
                .build(args);
        app.run();
    }

}
