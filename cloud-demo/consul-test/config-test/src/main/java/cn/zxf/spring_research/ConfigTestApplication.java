package cn.zxf.spring_research;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import cn.zxf.spring_research.biz.UserConfig;

@EnableConfigurationProperties( { UserConfig.class } )
@EnableFeignClients
@SpringBootApplication
public class ConfigTestApplication {

    public static void main( String[] args ) {
        System.setProperty( "spring.profiles.active", "test" );
        SpringApplication.run( ConfigTestApplication.class, args );
    }

}
