package cn.zxf.spring_research.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <br>
 * Created by ZXFeng on 2023/5/25
 */
@Configuration
@EnableConfigurationProperties(CosPropertiesBck.class)
public class CosConfig {

    @Autowired
    CosPropertiesBck cosProp;

    @Bean
    public String secretId() {
        return cosProp.getSecretId();
    }

}
