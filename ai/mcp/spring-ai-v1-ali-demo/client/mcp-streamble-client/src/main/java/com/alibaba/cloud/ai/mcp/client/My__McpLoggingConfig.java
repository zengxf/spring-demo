package com.alibaba.cloud.ai.mcp.client;

import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * <p/>
 * Created by ZXFeng on 2026/2/11
 */
@Configuration
public class My__McpLoggingConfig {

    // @Bean
    // public RestClientCustomizer dashScopeRestClientCustomizer() {
    //     return restClientBuilder -> restClientBuilder.requestInterceptor(new My__LoggingInterceptor());
    // }

    @Bean
    public RestClientCustomizer dashScopeRestClientCustomizer() {
        return restClientBuilder -> {
            // 关键：允许响应流被多次读取
            restClientBuilder.requestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
            restClientBuilder.requestInterceptor(new My__LoggingInterceptor());
        };
    }


}
