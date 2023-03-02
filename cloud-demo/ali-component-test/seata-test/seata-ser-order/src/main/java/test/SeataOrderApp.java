package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
public class SeataOrderApp {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderApp.class, args);
    }

}
