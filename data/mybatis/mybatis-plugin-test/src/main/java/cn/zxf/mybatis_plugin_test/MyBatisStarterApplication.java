package cn.zxf.mybatis_plugin_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zxf.mybatis_plugin_test")
public class MyBatisStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisStarterApplication.class, args);
    }

}
