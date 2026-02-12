package com.alibaba.cloud.ai.mcp.server;

import com.alibaba.cloud.ai.mcp.server.service.TimeService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StreamableWebMVCServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamableWebMVCServerApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider timeTools(TimeService timeService) {
        return MethodToolCallbackProvider.builder().toolObjects(timeService).build();
    }
}
