package test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.handler.ExceptionHandler;

/**
 * 异常处理配置类
 */
@Configuration
@AutoConfigureBefore(ErrorWebFluxAutoConfiguration.class)
@EnableConfigurationProperties({ServerProperties.class})
public class ExceptionHandlerConfiguration {

    @Bean
    public ExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
        return new ExceptionHandler(objectMapper);
    }

}
