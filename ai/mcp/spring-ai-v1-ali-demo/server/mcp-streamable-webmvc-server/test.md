# MCP 服务器测试指南

## Spring Boot Actuator 查看
- http://localhost:20000/actuator
## CURL 测试

- **bash 中运行**
- **_用 curl 有问题，可以用 Bruno 或 Postman 测试_**

```bash
# 查看基本信息
curl -X POST \
-H 'accept: application/json, text/event-stream' \
-d '
{
  "jsonrpc": "2.0",
  "id": "1",
  "method": "initialize",
  "params": {
    "protocolVersion": "2026-02-05"
  }
}
' \
http://localhost:20000/mcp

# ----------------------
# ----------------------

# 查看有哪些工具
# session-id 可以通过启动 client 获取
curl -X POST \
-H 'accept: application/json, text/event-stream' \
-H 'mcp-session-id: 45c9e724-e3c6-42bb-950d-5d04bc502c38' \
-d '
{
  "jsonrpc": "2.0",
  "id": "1",
  "method": "tools/list",
  "params": {}
}
' \
http://localhost:20000/mcp

# 响应
id:45c9e724-e3c6-42bb-950d-5d04bc502c38
event:message
data:{"jsonrpc":"2.0","id":"1","result":{"tools":[{"name":"getCityTimeMethod","description":"获取指定城市的时间。","inputSchema":{"type":"object","properties":{"timeZoneId":{"type":"string","description":"时区 ID，如 Asia/Shanghai"}},"required":["timeZoneId"],"additionalProperties":false}}]}}
```

### inspector

```bash
npx -y @modelcontextprotocol/inspector --transport sse http://localhost:20000/mcp
```

- 运行后，页面设置如下
    - Transport Type: `Streamable HTTP`
    - URL: `http://localhost:20000/mcp`
    - Connection Type: `Via Proxy`
- 然后点 **Connect**
- **_只能测试 `initialize` `resources/list` `resources/templates/list` 几个简单命令_**