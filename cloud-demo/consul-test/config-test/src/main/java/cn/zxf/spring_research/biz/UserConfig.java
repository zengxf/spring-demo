package cn.zxf.spring_research.biz;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "user")
@Data
public class UserConfig {

    private String name;
    private Integer age;
    
}
