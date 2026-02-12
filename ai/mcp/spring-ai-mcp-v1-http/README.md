# Spring AI MCP Server Demo

这是一个使用 Spring AI 的 `spring-ai-starter-mcp-server` 实现的 MCP (Model Context Protocol) 服务器示例项目。

## 项目简介

本项目演示了如何使用 Spring AI 创建一个 MCP 服务器,提供多个工具供 AI 模型调用。项目包含两个示例工具:
- **计算器工具**: 提供加减乘除等基本数学运算
- **天气查询工具**: 提供城市天气查询功能(模拟数据)

## 项目结构

```
spring-ai-mcp-stdio/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── mcp/
│       │               ├── McpDemoApplication.java      # Spring Boot 主应用类
│       │               └── tools/
│       │                   ├── CalculatorTool.java      # 计算器工具
│       │                   └── WeatherTool.java         # 天气查询工具
│       └── resources/
│           └── application.yml                          # 应用配置文件
├── build.gradle                                         # Gradle 构建配置
└── README.md
```

## 功能说明

### 1. 计算器工具 (CalculatorTool)

提供以下数学运算功能:
- `add(a, b)`: 加法运算
- `subtract(a, b)`: 减法运算
- `multiply(a, b)`: 乘法运算
- `divide(a, b)`: 除法运算(包含除零检查)

### 2. 天气查询工具 (WeatherTool)

提供城市天气查询功能:
- `getWeather(city)`: 查询指定城市的天气信息
- `getSupportedCities()`: 获取支持查询的城市列表

支持的城市: 北京、上海、广州、深圳、成都

## 环境要求

- JDK 21 或更高版本
- Gradle 9.2 或更高版本
- Spring Boot 3.4.1
- Spring AI 1.1.2

## 配置说明

在 `application.yml` 中配置 MCP 服务器:

```yaml
spring:
  ai:
    mcp:
      server:
        enabled: true                          # 启用 MCP 服务器
        name: Spring AI MCP Demo Server       # 服务器名称
        version: 1.0.0                         # 服务器版本
        transport: stdio                       # 传输方式(标准输入输出)
```

MCP 服务器使用 stdio 传输方式,通过标准输入输出与客户端通信。
