package com.example.mcp.config;

import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * <p/>
 * Created by ZXFeng on 2026/1/30
 */
// @Configuration
@Slf4j
@Deprecated(since = "McpSyncClient that could not be found.")
public class McpClientTester {

    @Bean
    public CommandLineRunner testMcpClient(McpSyncClient mcpClient) {
        log.info("=====================");
        return args -> {
            System.out.println("Testing MCP Client connection...");

            // 列出所有可用工具
            var toolsRes = mcpClient.listTools();
            List<McpSchema.Tool> tools = toolsRes.tools();
            System.out.println("Available tools:");
            tools.forEach(tool -> System.out.println(" - " + tool.name() + ": " + tool.description()));

            // 如果有工具，可以尝试调用一个
            if (!tools.isEmpty()) {
                String toolName = "getSupportedCities";
                var result = mcpClient.callTool(new McpSchema.CallToolRequest(toolName, Map.of()));
                System.out.println("Result from calling tool '" + toolName + "': " + result);
            }
        };
    }

}
