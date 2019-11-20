package error2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class ErrorDemoAutoConf {
    @Order( 2 )
    @Bean
    public BaseDemo3 baseDemo3() {
        return new BaseDemo3();
    }

    @Order( 1 )
    @Bean
    public BaseDemo4 baseDemo4() {
        return new BaseDemo4();
    }
}
