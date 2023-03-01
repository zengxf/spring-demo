# 网关服务

- path 以 `/` 开头匹配
- 通配符是 `**`

---

## 测试 URL

### my-ser1

- http://localhost:8090/my-ser-1/api/biz/echo
- http://localhost:8090/my-ser-a/api/biz/echo
- 添加的 `AddResponseHeader` 要 F12 查看

### my-ser2

- http://localhost:8090/my-ser-2/api/biz/hello

### baidu

- http://localhost:8090/baidu-lb/s?wd=test
- http://localhost:8090/baidu/s?wd=test

---

## Sentinel

- 默认的链路名称前缀是：`ReactiveCompositeDiscoveryClient_`
    - 如：`ReactiveCompositeDiscoveryClient_my-ser-1`
- 配置参考：https://juejin.cn/post/7130392890299121700 