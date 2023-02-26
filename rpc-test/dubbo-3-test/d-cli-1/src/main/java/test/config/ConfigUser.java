package test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/23.
 */
@Data
@Component
@ConfigurationProperties(prefix = "my.config.user")
public class ConfigUser {

    private String name;
    private Integer age;

}
