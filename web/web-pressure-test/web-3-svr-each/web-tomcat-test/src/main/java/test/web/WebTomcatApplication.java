package test.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WebTomcatApplication {

    public static void main(String[] args) {
        log.info("start ------------------------------");
        long start = System.currentTimeMillis();

        SpringApplication.run(WebTomcatApplication.class, args);

        long use = System.currentTimeMillis() - start;
        log.info("启动完成！use: [{}] ms", use);
    }

}
