package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
@EnableTransactionManagement
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SeataSerMainApp {

    public static void main(String[] args) {
        SpringApplication.run(SeataSerMainApp.class, args);
    }

}
