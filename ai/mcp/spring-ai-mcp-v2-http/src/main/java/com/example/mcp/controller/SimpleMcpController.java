package com.example.mcp.controller;

import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p/>
 * Created by ZXFeng on 2026/1/30
 */
@RestController
public class SimpleMcpController {

    // 自动注入 Spring AI Starter 创建好的 MCP Server 实例
    @Autowired
    private McpSyncServer mcpServer;

    /**
     * 同步包装接口：直接接收 JSON-RPC 请求并等待结果返回
     */
    // @PostMapping("/direct-mcp")
    // public McpSchema.JsonRpcResponse handleDirectRequest(@RequestBody McpSchema.JsonRpcRequest request) {
    //     // 直接调用 server 逻辑，跳过 SSE 传输层
    //     return mcpServer.handleRequest(request);
    // }

    /**
     * list tools
     * <p/>
     * http://localhost:8056/listTools
     */
    @GetMapping("/listTools")
    public List<McpSchema.Tool> listTools() {
        return mcpServer.listTools();
    }

}
