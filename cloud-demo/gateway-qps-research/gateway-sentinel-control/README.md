# Gateway Sentinel Control

基于 Sentinel 集群流控的网关模块,作为 Sentinel 集群的客户端,支持多实例部署并对上游服务进行统一流控。

## 模块说明

本模块是一个 Spring Cloud Gateway 网关,集成了 Sentinel 集群流控客户端功能。多个网关实例可以连接到同一个 Sentinel 集群服务端,实现跨实例的统一流量控制。

## 核心特性

- **集群流控**: 作为 Sentinel 集群客户端,连接到集群服务端进行统一流控
- **多实例支持**: 支持多个网关实例部署,共享流控配额
- **服务级流控**: 对上游服务进行流控,而不是对单个接口
- **自动服务发现**: 集成 Nacos,自动发现上游服务
- **负载均衡**: 支持对上游服务的负载均衡和重试机制

## 架构说明

```
客户端请求 -> Gateway实例1 \
                            -> Sentinel集群服务端 -> 流控决策
客户端请求 -> Gateway实例2 /
```

多个网关实例共享同一个流控配额,由 Sentinel 集群服务端统一管理。

## 依赖服务

1. **Nacos**: 服务注册与发现 (默认: localhost:8848)
2. **Sentinel 集群服务端**: 流控决策服务 (默认: localhost:18730)
3. **上游服务**: 如 spring-web-1, spring-web-2 等

## 配置说明

### application.yml 配置项

```yaml
server:
  port: 9662  # 网关端口

spring:
  application:
    name: gateway-sentinel-control
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848  # Nacos 地址
    gateway:
      discovery:
        locator:
          enabled: true  # 启用服务发现
          lower-case-service-id: true

# Sentinel 集群客户端配置
sentinel:
  cluster:
    client:
      enabled: true
      server-host: localhost  # Sentinel 集群服务端地址
      server-port: 18730      # Sentinel 集群服务端端口
      request-timeout: 2000   # 请求超时时间(ms)
```

### 流控规则配置

在 `FlowRuleConfig.java` 中配置流控规则:

```java
// 为 spring-web-1 服务配置流控规则
FlowRule web1Rule = new FlowRule();
web1Rule.setResource("spring-web-1");  // 资源名(服务名)
web1Rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
web1Rule.setCount(10);  // QPS 限制为 10
web1Rule.setClusterMode(true);  // 启用集群模式
```

## 启动步骤

### 1. 启动依赖服务

```bash
# 启动 Nacos
# 启动 Sentinel 集群服务端 (sentinel-cluster-service)
# 启动上游服务 (spring-web-1, spring-web-2)
```

### 2. 启动网关实例

```bash
# 启动第一个实例
./gradlew :gateway-sentinel-control:bootRun

# 启动第二个实例(修改端口)
./gradlew :gateway-sentinel-control:bootRun --args='--server.port=9663'
```

### 3. 测试流控效果

```bash
# 通过网关访问 spring-web-1 服务
curl http://localhost:9662/spring-web-1/test

# 通过第二个网关实例访问
curl http://localhost:9663/spring-web-1/test
```

## 访问方式

网关支持通过服务名访问上游服务:

```
http://localhost:9662/{服务名}/{接口路径}
```

示例:
- `http://localhost:9662/spring-web-1/test`
- `http://localhost:9662/spring-web-2/hello`

## 流控效果

当请求超过配置的 QPS 限制时,网关会返回:

```json
{
  "code": 429,
  "message": "请求过于频繁,请稍后再试",
  "type": "FlowException"
}
```

## 核心组件说明

### 1. SentinelClusterClientConfig
配置网关作为 Sentinel 集群客户端,连接到集群服务端。

### 2. FlowRuleConfig
配置流控规则,定义对各个上游服务的 QPS 限制。

### 3. SentinelGatewayFilter
网关过滤器,拦截所有请求并进行流控检查。

### 4. GlobalExceptionHandler
全局异常处理器,统一处理网关异常。

## 集群流控原理

1. 多个网关实例作为 Sentinel 集群客户端
2. 所有流控请求发送到 Sentinel 集群服务端
3. 集群服务端统一计算和管理流量配额
4. 实现跨实例的精确流控

## 与 spring-gateway 模块的区别

| 特性 | spring-gateway | gateway-sentinel-control |
|------|----------------|--------------------------|
| 流控模式 | 单机流控 | 集群流控 |
| 多实例部署 | 每个实例独立限流 | 多实例共享配额 |
| 流控精度 | 单实例精确 | 集群精确 |
| 依赖服务 | Nacos | Nacos + Sentinel 集群服务端 |

## 注意事项

1. 确保 Sentinel 集群服务端已启动并正常运行
2. 网关实例需要能够访问集群服务端的端口 (默认 18730)
3. 流控规则在代码中配置,也可以通过 Nacos 动态配置
4. 建议在生产环境使用 Nacos 作为规则数据源,实现动态更新

## 扩展建议

1. **动态规则配置**: 集成 Nacos 数据源,实现流控规则的动态更新
2. **监控告警**: 集成 Sentinel Dashboard,实时监控流控情况
3. **降级熔断**: 添加降级和熔断规则,提高系统稳定性
4. **自定义资源名**: 根据业务需求自定义资源名提取逻辑
