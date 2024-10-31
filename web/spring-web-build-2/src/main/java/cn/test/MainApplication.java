package cn.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.time.LocalTime;
import java.util.List;

/**
 * <p/>
 * Created by ZXFeng on 2023/11/22
 */
@SpringBootApplication
@Slf4j
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args); // run boot
    }

}
