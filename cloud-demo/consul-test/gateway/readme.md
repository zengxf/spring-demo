# 利用 Consul 测试 
## 测试 url
- server-a: http://localhost:9101/a-api/api/biz/hello
- server-b: http://localhost:9101/b-api/api/biz/hello
- curl: `curl -v -H "X1-Test:test-a" http://localhost:9101/a-api/api/biz/hello`
- curl: `curl -v -H "X1-Test:test-b" http://localhost:9101/b-api/api/biz/hello`

## 问题及解决
- 自定义的过滤器配置，要重写 `shortcutFieldOrder()` 方法，不然获取不到配置

### 设置过滤器顺序
- 用 `@Order(-10)` 无效
- 要用 `implements Ordered` 才有效