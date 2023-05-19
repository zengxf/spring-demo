package cn_zxf_test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @ComponentScan(lazyInit = true)
@Slf4j
public class MainApplication {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        SpringApplication.run(MainApplication.class, args);

        long us = System.currentTimeMillis() - start;
        log.info("\n\n-------------------- 启动用时：[{}] \n\n", fmtMs(us));
    }

    static String fmtMs(long ms) {
        long mm = ms / 60_000L;
        long ss = ms / 1000L % 60L;
        long m = ms % 1000L;
        return String.format("%s:%s.%s", mm, ss, m);
    }

}
