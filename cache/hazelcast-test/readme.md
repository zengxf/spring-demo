# Hazelcast 测试
- [原文参考](https://juejin.im/post/5bea40f2f265da61380ec206)

## 管理端
### 下载
- https://hazelcast.org/download/#management-center

### 启动
- `sh ./start.sh 8200 mancenter`
- 访问：http://127.0.0.1:8200/mancenter
- 设置密码时，字母和数字不能连用超过 2 位

## 问题
- `@CacheConfig( cacheNames = "instruments-xx" )` 要设置，但没对应上也不会错。可当作 key