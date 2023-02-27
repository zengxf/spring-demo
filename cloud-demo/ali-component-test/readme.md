# 测试 Spring-Cloud-Alibaba 组件

## 项目说明

- my-ser-1 用到了 Sentinel
- my-ser-2 用到了 LoadBalancer

### Sentinel 使用说明

- 直接去 Nacos 添加规则，不要直接去 dashboard 界面添加

---

## 遇到的问题

### 读取不到 Nacos 配置

- 没有读取 bootstrap.yml，启用 bootstrap

### 没有读取 bootstrap.yml

- 解决：
    - 添加依赖：`implementation group: 'org.springframework.cloud', name: 'spring-cloud-starter-bootstrap'`

### Lombok 插件不能用

- 解决：直接到 `dependencies` 处理

### Nacos 注册不了

- 与 Spring-Cloud 版本不兼容，降低其版本，Ali 的不用降

### Sentinel 没集成成功

- yml 配置多了 `nacos` 一层，去掉就是了
- 可通过以下代码检测

```java
public class MyConfig {
    @Value("${spring.cloud.sentinel.transport.dashboard}")
    private String sentinelDashboard;
}
```

- 还有种原因：是懒加载，**要调用系统的相关链路才会出现**

### Feign 执行出错

- OpenFeign 与 LoadBalancer 两依赖其实并不冲突，都是一个版本号
- 原因是低版本的 `alibaba-nacos-discovery` 引用了 `spring-cloud-starter-netflix-ribbon`
    - 所以默认使用了 `RibbonLoadBalancerClient`
    - 而 `spring-cloud-netflix-ribbon` 版本过老
    - 里面的 `RibbonLoadBalancerClient` 没有实现 `choose(String, Request<T>)` 方法
- 解决：参考 `FeignConfig`，手动指定 `LoadBalancerClient`
    - 或使用 `spring-cloud-alibaba` 最新版本 `2021.1`
    - 其引用的是 `spring-cloud-3.0.1` 并不冲突

### Dubbo 2.7 在 JDK 17 中启动报错

- 添加 JVM 参数：
    - **但在单元测试中没用，单测出错**

```
--add-opens java.base/java.lang=ALL-UNNAMED 
--add-opens java.base/sun.reflect.generics.reflectiveObjects=ALL-UNNAMED
--add-opens java.base/java.math=ALL-UNNAMED
```

- 或使用 `dubbo-3.0.6` 以上版本

### 升级 Dubbo 3 失败

- 原因是与旧的 Nacos 不兼容

### Feign 日志

- `FeignConfig` 和 logback 都要配置

---

## 参考

- Sentinel 介绍 https://www.cnblogs.com/cbvlog/p/15385100.html
- OpenFeign 介绍 https://www.cnblogs.com/cbvlog/p/15322926.html

### 参考类和方法

- OpenFeign
    - 配置 `org.springframework.cloud.openfeign.FeignAutoConfiguration`
    - Sentinel `com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration`
    - 调用 `feign.SynchronousMethodHandler#executeAndDecode`
- LoadBalancer
    - 接口 `org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancer`
    - 接口 `org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer`
    - 使用 `org.springframework.cloud.loadbalancer.blocking.client.BlockingLoadBalancerClient`
- Sentinel
    - 令牌桶 `com.alibaba.csp.sentinel.slots.block.flow.controller.DefaultController`
    - 漏桶 `com.alibaba.csp.sentinel.slots.block.flow.controller.RateLimiterController`
    - 预热令牌桶 `com.alibaba.csp.sentinel.slots.block.flow.controller.WarmUpController`
    - 预热漏桶 `com.alibaba.csp.sentinel.slots.block.flow.controller.WarmUpRateLimiterController`
    - 加载 Nacos `com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource`
    - 配置 `com.alibaba.cloud.sentinel.custom.SentinelAutoConfiguration`
    - 转换 `com.alibaba.cloud.sentinel.custom.SentinelAutoConfiguration.SentinelConverterConfiguration`
    - 熔断实现 `com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.ExceptionCircuitBreaker`