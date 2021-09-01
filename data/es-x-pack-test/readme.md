# ES-x-pack 测试
- ES 版本： `elasticsearch-7.14.0-no-jdk`
- 启用 x-pack 参考： https://www.jianshu.com/p/40e35c6a32a4

## 使用 Spring-Data 遇到的问题及解决
- 实体类要用 `@Document(indexName = "user")` 注解
  - `indexName` 必须是小写
- 创建索引时，需要设置 `number_of_shards`