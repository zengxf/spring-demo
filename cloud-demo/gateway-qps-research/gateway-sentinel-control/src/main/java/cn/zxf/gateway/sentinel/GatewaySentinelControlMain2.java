package cn.zxf.gateway.sentinel;

import org.springframework.boot.SpringApplication;

public class GatewaySentinelControlMain2 {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "port2");
        SpringApplication.run(GatewaySentinelControlMain1.class, args);
    }

}
