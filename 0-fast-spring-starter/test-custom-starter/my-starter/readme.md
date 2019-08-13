# 自制 starter

- [2.6 原文参考](https://www.jianshu.com/p/45538b44e04e)

## 代码解释
- `@ConditionalOnProperty(prefix = "example.service",value = "enabled",havingValue = "true")`
- - 当配置文件中有 `example.service.enabled=true` 时，启用

## 发布
- `gradle install`

### 其他工程依赖
- `repositories { mavenLocal() }`
- `compile group: 'cn.zxf', name: 'my-starter', version: '1.0.0'`