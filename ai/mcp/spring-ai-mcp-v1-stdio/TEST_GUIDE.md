# MCP 服务器测试指南

## 测试方式

你的 MCP 服务器已经在 `http://localhost:8026` 运行。

## 方式 1: 使用浏览器或 curl 测试 REST API

我已经创建了 REST API 测试接口，可以直接测试工具功能。

### 计算器工具测试

**1. 测试加法**
```bash
curl "http://localhost:8026/api/test/add?a=10&b=20"
```
浏览器访问: http://localhost:8026/api/test/add?a=10&b=20

**2. 测试减法**
```bash
curl "http://localhost:8026/api/test/subtract?a=50&b=30"
```
浏览器访问: http://localhost:8026/api/test/subtract?a=50&b=30

**3. 测试乘法**
```bash
curl "http://localhost:8026/api/test/multiply?a=5&b=8"
```
浏览器访问: http://localhost:8026/api/test/multiply?a=5&b=8

**4. 测试除法**
```bash
curl "http://localhost:8026/api/test/divide?a=100&b=4"
```
浏览器访问: http://localhost:8026/api/test/divide?a=100&b=4

**5. 测试除零错误**
```bash
curl "http://localhost:8026/api/test/divide?a=10&b=0"
```
浏览器访问: http://localhost:8026/api/test/divide?a=10&b=0

### 天气查询工具测试

**1. 查询北京天气**
```bash
curl "http://localhost:8026/api/test/weather?city=北京"
```
浏览器访问: http://localhost:8026/api/test/weather?city=北京

**2. 查询上海天气**
```bash
curl "http://localhost:8026/api/test/weather?city=上海"
```
浏览器访问: http://localhost:8026/api/test/weather?city=上海

**3. 获取支持的城市列表**
```bash
curl "http://localhost:8026/api/test/cities"
```
浏览器访问: http://localhost:8026/api/test/cities

---

## 方式 2: 使用 Claude Desktop 测试 MCP 服务器

MCP 服务器的主要用途是与 AI 客户端（如 Claude Desktop）集成。

### 配置步骤

1. **找到 Claude Desktop 配置文件**
   - Windows: `%APPDATA%\Claude\claude_desktop_config.json`
   - macOS: `~/Library/Application Support/Claude/claude_desktop_config.json`

2. **添加 MCP 服务器配置**

将以下内容添加到配置文件中：

```json
{
  "mcpServers": {
    "spring-ai-mcp-stdio": {
      "command": "java",
      "args": [
        "-jar",
        "D:\\Data\\llm\\temp\\spring-ai-mcp-stdio\\build\\libs\\spring-ai-mcp-stdio-1.0.0.jar"
      ]
    }
  }
}
```

3. **重启 Claude Desktop**

4. **测试工具**
   - 在 Claude Desktop 中询问："帮我计算 25 + 37"
   - 或询问："北京今天天气怎么样？"


## Inspector 界面测试
- **cmd 中运行**
```bash
# 启动 inspector 界面 (要先设置 JDK 版本)

set JAVA_HOME=D:\Install\Java\JDK\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%

# 测试
java -jar D:/Data/llm/temp/spring-ai-mcp-stdio/build/libs/spring-ai-mcp-stdio-1.0.0.jar

# 有问题
# Error from MCP server: SyntaxError: Unexpected end of JSON input
#     at JSON.parse (<anonymous>)
npx -y @modelcontextprotocol/inspector java -jar D:/Data/llm/temp/spring-ai-mcp-stdio/build/libs/spring-ai-mcp-stdio-1.0.0.jar
```