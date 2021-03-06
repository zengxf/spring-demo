package cn.zxf.spring_research;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication
public class ServerAApplication {

    public static void main( String[] args ) {
        SpringApplication.run( ServerAApplication.class, args );
    }

}
