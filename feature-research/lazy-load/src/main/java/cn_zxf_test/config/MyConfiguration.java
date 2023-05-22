package cn_zxf_test.config;

import cn_zxf_test.config.annotation.MyTest1Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <br>
 * Created by ZXFeng on 2023/5/22
 */
@Configuration
public class MyConfiguration {

    @Bean("my1Service")
    public MyTest1Service my1Service() {
        return new MyTest1Service();
    }

}
