# 利用 Consul 测试 
## key 的格式
- `$prefix/$defaultContext$profileSeparator$profile/$data-key`
- 如：`zxf/config::dev/data`

### 测试配置的值
```
test:
  value: test-00
user:
  name: zxf
  age: 22
spring:
  data:
    mongodb:
      uri: mongodb://localhost:5501/test1
      #uri: mongodb://localhost:5501/test2
```

## 坑
- 修改配置后会自动刷新
  - 能改 `@ConfigurationProperties` 注解的值
  - 但不会改 `@Value` 注解的值
  - 也不能重新设置 MongoDB 连接