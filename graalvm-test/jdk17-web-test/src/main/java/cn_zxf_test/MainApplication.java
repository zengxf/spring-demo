package cn_zxf_test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * <p/>
 * Created by ZXFeng on 2023/11/22
 */
@SpringBootApplication
@Slf4j
public class MainApplication {

    public static void main(String[] args) {
        log.info("main-args: [{}]", Arrays.toString(args));
        SpringApplication.run(MainApplication.class, args);
    }

}
