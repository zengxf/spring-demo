# 动态数据源测试
## 总结
- 不要设置 `JdbcTemplate`
- 直接使用 `@DS("test02")` 改数据源
- 原理：`DynamicDataSourceAutoConfiguration` 重新定义了数据源 `DynamicRoutingDataSource`