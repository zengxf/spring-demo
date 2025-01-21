# 用于测试 Nginx

## 测试

### 基础测试

```shell
curl --header "Content-Type: application/json" \
  --request GET \
  --data '{"key1": "v1-009"}' \
  http://localhost:9901/test/base?test1=v1&key2=v2
```
