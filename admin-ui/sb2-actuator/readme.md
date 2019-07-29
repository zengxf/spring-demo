# Spring-Boot 2 监控测试
- 默认使用 **Micrometer**

## 启用 Prometheus
- 添加依赖 `micrometer-registry-prometheus`
- 路径查看 `http://localhost:9001/actuator/prometheus`

### 启用
```
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

### Prometheus 配置
```
# prometheus.yml
  - job_name: 'java-sb2'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:9001']
        labels:
          application: 'zxf-java'
          instance: 'local'
```

## Grafana 配置
- 面板导入 `4701`