# 利用 Consul 测试 
## 坑
- 要用 `actuator` 提供健康检查，否则发现不了服务
- `@EnableFeignClients` 和 `@EnableDiscoveryClient` 只能配置一次
- **OpenFeign** 里面的接口-方法参数声明 `@PathVariable("id")` 要设置名称
- **OpenFeign** 里面的接口-方法 URL 要声明全，否则 404 错误
- 调用提示负载均衡错误，可能是服务提供端没启动
  - 例：`ClientException: Load balancer does not have available server for client: server-b`

## 备注
- `@EnableDiscoveryClient` 可以不用声明
- 程序正常退出时，默认会注销服务，可用 `deregister` 配置

### 设置公网 IP
- 添加 `inetutils` 配置
- 这个配置是 spring cloud 的网络工具类
- 这个配置的含义是如果获取 IP 时获取到多个 IP（内网、外网）
- 优先选择配置的 IP 中存在的 IP
- 这样注册 service 时就变成公网 IP
- 域名访问时，还要使用 `prefer-ip-address` 配置，指示用 IP 注册而不是主机名注册