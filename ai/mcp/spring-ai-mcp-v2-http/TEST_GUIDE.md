# MCP 服务器测试指南

## CURL 测试
- **bash 中运行**
```bash
# protocol: SSE 可以
curl http://localhost:8056/sse
输出
event:endpoint
data:/mcp/message?sessionId=88065c9b-f3a3-40ba-b3c7-6993b3d3cd14

# 不行
curl -X POST http://localhost:8056/mcp/message?sessionId=88065c9b-f3a3-40ba-b3c7-6993b3d3cd14 \
  -H "Content-Type: application/json" \
  -d '{"jsonrpc":"2.0","method":"tools/list","id":1}'
```

### inspector
```bash
npx -y @modelcontextprotocol/inspector --transport sse http://localhost:8056/sse

# Web 设置
#   Transport Type: SSE
#   URL: http://localhost:8056/sse
#   Connection Type: Via Proxy
```

### Claude Desktop 配置
```json
{
  "mcpServers": {
    "my-spring-ai-server": {
      "command": "npx",
      "args": [
        "-y",
        "@modelcontextprotocol/inspector",
        "--transport sse http://localhost:8056/sse"
      ]
    }
  }
}
```