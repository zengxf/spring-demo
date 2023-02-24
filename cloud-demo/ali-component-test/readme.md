# 测试 Spring-Cloud-Alibaba 组件

## 遇到的问题

### 没有读取 bootstrap.yml

- 解决：
    - 添加依赖：`implementation group: 'org.springframework.cloud', name: 'spring-cloud-starter-bootstrap'`

### lombok 插件不能用

- 解决：直接到 `dependencies` 处理