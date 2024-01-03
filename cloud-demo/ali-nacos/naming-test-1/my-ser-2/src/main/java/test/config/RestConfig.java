package test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
@Configuration
public class RestConfig {

    @Bean
    @ConfigurationProperties(prefix = "rest.template.config")
    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        // return new RestTemplate();
        return new RestTemplate(customHttpRequestFactory());
    }

}
