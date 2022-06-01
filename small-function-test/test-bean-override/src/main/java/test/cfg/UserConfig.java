package test.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.biz.UserManager;

/**
 * <br/>
 * Created by ZXFeng on 2022/6/1.
 */
@Configuration
public class UserConfig {

    @Value("${user.name:ZXF}")
    private String userName;

    @Bean
    public UserManager userManager() {
        return new UserManager(this.userName);
    }

}
