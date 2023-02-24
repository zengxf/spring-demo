# 测试 Spring-Cloud-Alibaba 组件

## 项目说明

- my-ser-1 用到了 Sentinel
- my-ser-2 用到了 LoadBalancer

---

## 遇到的问题

### 读取不到 Nacos 配置

- 没有读取 bootstrap.yml，启用 bootstrap

### 没有读取 bootstrap.yml

- 解决：
    - 添加依赖：`implementation group: 'org.springframework.cloud', name: 'spring-cloud-starter-bootstrap'`

### lombok 插件不能用

- 解决：直接到 `dependencies` 处理

### Nacos 注册不了

- 与 Spring-Cloud 版本不兼容，降低版本

### Sentinel 没集成成功

- yml 配置多了 `nacos` 一层，去掉就是了
- 通过以下代码检测出来的

```java
// @Value("${spring.cloud.sentinel.transport.dashboard}")
// private String sentinelDashboard;
```