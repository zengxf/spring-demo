# MCP 服务器测试指南

## 方式 1: 使用浏览器或 curl 测试 REST API

我已经创建了 REST API 测试接口，可以直接测试工具功能。

### 计算器工具测试

**1. 测试加法**
```bash
curl "http://localhost:8026/api/test/add?a=10&b=20"
```
浏览器访问: http://localhost:8026/api/test/add?a=10&b=20

## CURL 测试
- **bash 中运行**
```bash
# protocol: SSE 可以
curl http://localhost:8026/sse

curl -X POST http://localhost:8026/mcp/message?sessionId=79180d1e-08bd-46da-bff1-6e64cd95c6b8 \
  -H "Content-Type: application/json" \
  -d '{"jsonrpc":"2.0","method":"tools/list","id":1}'

npx -y @modelcontextprotocol/inspector --transport sse http://localhost:8026/sse
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
        "http://localhost:8026/sse"
      ]
    }
  }
}
```