# 利用 Consul 测试 
## 坑
- 要用 `actuator` 提供健康检查，否则发现不了服务
- `@EnableFeignClients` 和 `@EnableDiscoveryClient` 只能配置一次
- **OpenFeign** 里面的接口-方法参数声明 `@PathVariable("id")` 要设置名称
- **OpenFeign** 里面的接口-方法 URL 要声明全，否则 404 错误
- 调用提示负载均衡错误，可能是服务提供端没启动
  - 例：`ClientException: Load balancer does not have available server for client: server-b`