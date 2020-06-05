# Spring 纯内存 Cache 测试

## 原理
- `@EnableCaching` 
  - `CachingConfigurationSelector`
- 值是：`PROXY`
- `AutoProxyRegistrar`
- `ProxyCachingConfiguration`
  - `CacheInterceptor`
  - `CacheAspectSupport#execute()` 最终调用