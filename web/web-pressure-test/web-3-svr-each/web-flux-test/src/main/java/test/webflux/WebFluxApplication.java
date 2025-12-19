package test.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WebFluxApplication {

    public static void main(String[] args) {
        log.info("start ------------------------------");
        long start = System.currentTimeMillis();

        SpringApplication.run(WebFluxApplication.class, args);

        long use = System.currentTimeMillis() - start;
        log.info("启动完成！use: [{}] ms", use);
    }

}
