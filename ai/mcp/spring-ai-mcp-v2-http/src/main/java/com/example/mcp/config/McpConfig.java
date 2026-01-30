package com.example.mcp.config;

import com.example.mcp.tools.CalculatorTool;
import com.example.mcp.tools.MyRes;
import com.example.mcp.tools.WeatherTool;
import lombok.extern.slf4j.Slf4j;
import org.springaicommunity.mcp.provider.resource.SyncMcpResourceProvider;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.ai.tool.metadata.ToolMetadata;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p/>
 * Created by ZXFeng on 2026/1/30
 */
@Configuration
@Slf4j
public class McpConfig {

    @Autowired
    private CalculatorTool calculatorTool;
    @Autowired
    private WeatherTool weatherTool;
    @Autowired
    private MyRes myRes;

    @Bean
    public ToolCallbackProvider toolCallbackProvider() {
        MethodToolCallbackProvider provider = MethodToolCallbackProvider.builder()
                .toolObjects(calculatorTool, weatherTool)
                .build();

        log.info("===========================");
        Stream.of(provider.getToolCallbacks())
                .forEach(tc -> {
                    ToolMetadata meta = tc.getToolMetadata();
                    ToolDefinition def = tc.getToolDefinition();
                    // log.info("meta: [{}], def: [{}]", meta, def);
                    log.info("meta-direct: [{}], def-name_desc: [{}: {}]",
                            meta.returnDirect(), def.name(), def.description()
                    );
                    // log.info("def-inSchema: \n{}", def.inputSchema()); // json 定义
                });
        log.info("===========================");

        return provider;
    }

    @Bean
    public SyncMcpResourceProvider resourceProvider() {
        SyncMcpResourceProvider provider = new SyncMcpResourceProvider(List.of(myRes));

        log.info("=========================== res");
        provider.getResourceSpecifications().forEach(spc -> {
            log.info("spc: {}", spc);
        });
        log.info("=========================== res");

        return provider;
    }

}
