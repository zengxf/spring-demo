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
        // System.out.printf("ConfigurableWebApplicationContext.class: [%s].%n", ConfigurableWebApplicationContext.class.getName());
        // System.out.printf("Servlet.class: [%s].%n", jakarta.servlet.Servlet.class.getName());
        // System.out.printf("Tomcat.class: [%s].%n", org.apache.catalina.startup.Tomcat.class.getName());

        System.out.println("1111111111111111111111111");
        ApplicationContextFactory factory = newInstance("org.springframework.boot.web.servlet.context.ServletWebServerApplicationContextFactory");

        SpringApplication app = new SpringApplicationBuilder(MainApplication.class)
                .web(WebApplicationType.SERVLET)
                .contextFactory(factory)
                .build();
        ConfigurableApplicationContext ctx = app.run(args); // run boot
        System.out.println("1111111111111111111111111");

        System.out.println("2222222222222222222222222");
        System.out.printf("web-type: [%s].%n", app.getWebApplicationType());
        List<ApplicationContextFactory> factories = SpringFactoriesLoader.loadFactories(
                ApplicationContextFactory.class,
                MainApplication.class.getClassLoader()
        );  // jLink 读取不到这些类
        System.out.printf("factories-size: [%s].%n", factories.size());
        for (ApplicationContextFactory candidate : factories) {
            System.out.printf("ctx-factory: [%s].%n", candidate.getClass());
        }
        checkClass("org.apache.catalina.startup.Tomcat");
        checkClass("org.springframework.boot.web.servlet.context.ServletWebServerApplicationContextFactory");
        System.out.println("2222222222222222222222222");

        System.out.printf("ctx-class: [%s].%n", ctx.getClass().getName());

        ServletWebServerApplicationContext web = (ServletWebServerApplicationContext) ctx;
        WebServer webSrv = web.getWebServer();
        System.out.printf("webSrv-class: [%s].%n", webSrv.getClass().getName());

        System.out.printf("启动完成 cut-time: [%s].%n", LocalTime.now());
        log.info("启动完成 cut-time: [{}]", LocalTime.now());   // jLink 没有打印日志
    }

    // bck
    public static void main0(String[] args) {
        SpringApplication.run(MainApplication.class, args); // run boot
    }

    private static void checkClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            System.out.printf("clazz: [%s].%n", clazz.getName());
        } catch (Exception e) {
            System.err.printf("校验 [%s] 出错.%n", className);
            e.printStackTrace();
        }
    }

    private static <T> T newInstance(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return (T) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

}
