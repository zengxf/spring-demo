package com.example.mcp;

import com.example.mcp.tools.CalculatorTool;
import com.example.mcp.tools.WeatherTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpDemoApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider weatherTools(CalculatorTool calculatorTool, WeatherTool weatherTool) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(calculatorTool, weatherTool)
                .build();
    }

}
